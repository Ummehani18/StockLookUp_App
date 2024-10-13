package com.example.stocklookup.network

import StockResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface StockApi {
    @GET("qu/quote") // Change endpoint to use query parameters
    suspend fun getStockData(
        @Query("symbol") symbol: String, // Use query parameter for symbol
        @Header("X-RapidAPI-Key") apiKey: String, // Use this header for the API key
        @Header("X-RapidAPI-Host") host: String   // Use this header for the host
    ): Response<StockResponse>
}
