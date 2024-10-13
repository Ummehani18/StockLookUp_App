
import com.google.gson.Gson

fun parseStockResponse(responseBody: String?): StockResponse? {
    val gson = Gson()
    return gson.fromJson(responseBody, StockResponse::class.java)
}
