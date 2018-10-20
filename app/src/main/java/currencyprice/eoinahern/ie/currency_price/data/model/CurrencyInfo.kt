package currencyprice.eoinahern.ie.currency_price.data.model

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CurrencyInfo(
		val symbol: String,
		val price: Float) : Comparable<CurrencyInfo> {

	override fun compareTo(other: CurrencyInfo): Int {

		return when {
			price  == other.price ->   0
			price > other.price ->  1
			else ->  -1
		}
	}
}