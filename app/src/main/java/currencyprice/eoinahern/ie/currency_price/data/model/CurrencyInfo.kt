package currencyprice.eoinahern.ie.currency_price.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class CurrencyInfo(
		@PrimaryKey
		val symbol: String,
		val price: Float,
		var isHigherOrEqual: Boolean = true) : Comparable<CurrencyInfo> {

	override fun compareTo(other: CurrencyInfo): Int {

		return when {
			price == other.price -> 0
			price > other.price -> 1
			else -> -1
		}
	}
}

fun CurrencyInfo.setIsHigherOrEqual(isHigherOrEqual: Boolean) {
	this.isHigherOrEqual = isHigherOrEqual
}

