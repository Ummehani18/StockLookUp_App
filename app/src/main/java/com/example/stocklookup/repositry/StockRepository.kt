package com.example.stocklookup.network

import StockResponse
import android.util.Log
import retrofit2.Response

class StockRepository {

    // Constants for API configuration
    private companion object {
        const val API_KEY = "b6657708b7mshcaed0032b7a2131p1093bfjsnd67de4cefcf2" // Replace with your actual RapidAPI key
        const val HOST = "apidojo-yahoo-finance-v1.p.rapidapi.com" // Host provided by RapidAPI
    }

    // Fetch stock data by passing the symbol to the Retrofit API using RapidAPI
    suspend fun fetchStockData(symbol: String): Response<StockResponse> {
        return try {
            // Call to Retrofit instance to fetch stock data from RapidAPI
            val response = RetrofitInstance.api.getStockData(
                symbol = symbol,
                apiKey = API_KEY,
                host = HOST
            )

            // Log the request for debugging
            Log.d("StockRepository", "Fetching stock data for symbol: $symbol")

            // Check if the response is successful
            if (response.isSuccessful) {
                response // Return the successful response
            } else {
                Log.e("StockRepository", "Error fetching stock data: ${response.errorBody()?.string()}")
                Response.error(response.code(), response.errorBody())
            }
        } catch (e: Exception) {
            // Handle exceptions such as network errors
            Log.e("StockRepository", "Error fetching stock data: ${e.localizedMessage}")
            Response.error(500, null) // Return an error response if the call fails
        }
    }
}
