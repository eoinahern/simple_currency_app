package currencyprice.eoinahern.ie.currency_price.data.model

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CurrencyInfo(
		val symbol: String,
		val price: Float)