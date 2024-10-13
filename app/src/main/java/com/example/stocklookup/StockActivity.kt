package com.example.stocklookup


import StockResponse
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.stocklookup.databinding.ActivityStockBinding
import com.example.stocklookup.network.StockRepository
import com.example.stocklookup.network.StockViewModel

class StockActivity : AppCompatActivity() {

    private lateinit var viewModel: StockViewModel
    private lateinit var binding: ActivityStockBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize StockRepository and ViewModel
        val repository = StockRepository() // Ensure this is correctly set up to handle API calls
        val factory = StockViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory).get(StockViewModel::class.java)

        // Set up observers for stock data, loading, and error states
        setupObservers()

        // Search button click event
        binding.btnSearch.setOnClickListener {
            val symbol = binding.searchStockSymbol.text.toString().trim()
            if (symbol.isNotEmpty()) {
                viewModel.fetchStockData(symbol) // Ensure this function is correctly implemented in ViewModel
            } else {
                Toast.makeText(this, "Please enter a stock symbol", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupObservers() {
        viewModel.stockData.observe(this) { stockResponse: StockResponse? ->
            updateStockUI(stockResponse)
        }

        viewModel.loading.observe(this) { isLoading: Boolean ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) { errorMsg: String? ->
            errorMsg?.let { handleError(it) }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateStockUI(stockResponse: StockResponse?) {
        if (stockResponse != null) {
            // Extract data from StockResponse
            val symbol = stockResponse.price.symbol
            val price = stockResponse.price.regularMarketPrice.toString()
            val change = stockResponse.price.regularMarketChange.toString()

            // Update UI elements
            binding.tvCompanyName.text = symbol
            binding.tvStockPrice.text = "Price: $price"
            binding.tvStockChange.text = "Change: $change"

            // Show stock data fields
            showStockDataFields()
        } else {
            // Hide stock data fields if no data available
            hideStockDataFields()
        }
    }

    private fun handleError(errorMsg: String) {
        hideStockDataFields() // Hide stock data fields on error
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
    }

    private fun showStockDataFields() {
        binding.tvCompanyName.visibility = View.VISIBLE
        binding.tvStockPrice.visibility = View.VISIBLE
        binding.tvStockChange.visibility = View.VISIBLE
    }

    private fun hideStockDataFields() {
        binding.tvCompanyName.visibility = View.GONE
        binding.tvStockPrice.visibility = View.GONE
        binding.tvStockChange.visibility = View.GONE
    }
}
