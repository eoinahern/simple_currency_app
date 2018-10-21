package currencyprice.eoinahern.ie.currency_price.tools

import currencyprice.eoinahern.ie.currency_price.data.model.CurrencyInfo
import currencyprice.eoinahern.ie.currency_price.data.model.setIsHigherOrEqual

fun List<CurrencyInfo>.mapIsHigher(oldList: List<CurrencyInfo>): List<CurrencyInfo> {

	return this.mapIndexed { i, currencyInfo ->
		currencyInfo.setIsHigherOrEqual(
				(currencyInfo >= oldList[i]))
		currencyInfo
	}
}