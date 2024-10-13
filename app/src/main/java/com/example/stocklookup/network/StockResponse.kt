data class StockResponse(
    val price: Price
)

data class Price(
    val regularMarketPrice: Double,
    val regularMarketChange: Double,
    val symbol: String
)
