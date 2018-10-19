package currencyprice.eoinahern.ie.currency_price.tools

import java.text.DecimalFormat
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FormatHelper @Inject constructor() {

	private val decimalFormat: DecimalFormat = DecimalFormat("0.000")

	fun formatCurrency(currency: Float): String = decimalFormat.format(currency)
}