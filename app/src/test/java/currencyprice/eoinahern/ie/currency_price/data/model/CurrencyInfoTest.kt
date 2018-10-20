package currencyprice.eoinahern.ie.currency_price.data.model

import org.junit.Assert
import org.junit.Test

class CurrencyInfoTest {

	private val currencyInfoEuro = CurrencyInfo("EUR", 1.001f)
	private val currencyInfoDollar = CurrencyInfo("USD", 0.89f)
	private val currencyInfoYen = CurrencyInfo("YEN", 0.69f)

	@Test
	fun compareTest() {

		val result1 = currencyInfoEuro.compareTo(currencyInfoDollar)
		Assert.assertEquals(1, result1)

		val result2 = currencyInfoYen.compareTo(currencyInfoDollar)
		Assert.assertEquals(-1, result2)
	}
}