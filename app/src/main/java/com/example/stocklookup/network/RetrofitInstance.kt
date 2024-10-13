import com.example.stocklookup.network.StockApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitInstance {

    // Base URL for Yahoo Finance API
    private const val BASE_URL = "https://yahoo-finance15.p.rapidapi.com/api/yahoo/"

    // Logging interceptor for network requests
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // OkHttp client with interceptors
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging) // Add logging interceptor for debugging
        .addInterceptor { chain ->
            // Create a request with necessary headers
            val request = chain.request().newBuilder()
                .addHeader("X-RapidAPI-Key", "b6657708b7mshcaed0032b7a2131p1093bfjsnd67de4cefcf") // Add your API key
                .addHeader("X-RapidAPI-Host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
                .build()
            chain.proceed(request) // Proceed with the modified request
        }.build()

    // Lazy initialization of Retrofit instance
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Set the base URL
            .client(client) // Set the OkHttp client
            .addConverterFactory(GsonConverterFactory.create()) // Add Gson converter for JSON parsing
            .build()
    }

    // Expose StockApi service
    val api: StockApi by lazy {
        retrofit.create(StockApi::class.java) // Create the StockApi instance
    }
}
