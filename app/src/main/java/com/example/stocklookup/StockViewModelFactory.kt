package com.example.stocklookup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stocklookup.network.StockRepository
import com.example.stocklookup.network.StockViewModel

class StockViewModelFactory(private val repository: StockRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Check if the ViewModel class is StockViewModel
        if (modelClass.isAssignableFrom(StockViewModel::class.java)) {
            return StockViewModel(repository) as T // Create StockViewModel instance
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}") // Improved error message
    }
}
