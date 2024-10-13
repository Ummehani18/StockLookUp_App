package com.example.stocklookup.network

import StockResponse
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class StockViewModel(private val repository: StockRepository) : ViewModel() {

    private val _stockData = MutableLiveData<StockResponse?>() // LiveData for stock response
    val stockData: LiveData<StockResponse?> get() = _stockData

    private val _loading = MutableLiveData<Boolean>() // Loading state
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String?>() // Error messages
    val error: LiveData<String?> get() = _error

    // Fetch stock data from API using repository
    fun fetchStockData(symbol: String) {
        viewModelScope.launch {
            _loading.value = true // Start loading
            _error.value = null // Reset previous error

            try {
                val response: Response<StockResponse> = repository.fetchStockData(symbol)

                // Log the entire response for debugging
                Log.d("StockViewModel", "Response: $response")
                Log.d("StockViewModel", "Response Body: ${response.body()}")
                Log.d("StockViewModel", "Response Code: ${response.code()}")
                Log.d("StockViewModel", "Response Message: ${response.message()}")

                if (response.isSuccessful) {
                    if (response.body() != null) {
                        _stockData.value = response.body() // Update stock data
                    } else {
                        handleResponseError(204, "No Content: The server returned an empty response.")
                    }
                } else {
                    handleResponseError(response.code(), response.message())
                }
            } catch (e: Exception) {
                _error.value = "Network Error: ${e.localizedMessage}" // Handle exceptions during the network call
                Log.e("StockViewModel", "Exception: ${e.localizedMessage}")
            } finally {
                _loading.value = false // Stop loading
            }
        }
    }

    // Error handling for specific HTTP response codes
    private fun handleResponseError(code: Int, message: String) {
        _error.value = when (code) {
            204 -> "No Content: The server returned no data."
            401 -> "Unauthorized: Check API key."
            404 -> "Not Found: The symbol could not be found."
            500 -> "Server Error: Please try again later."
            else -> "Error $code: $message"
        }
        Log.e("StockViewModel", "API Error: ${_error.value}")
    }
}
