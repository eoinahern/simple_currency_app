package currencyprice.eoinahern.ie.currency_price.data.api

import currencyprice.eoinahern.ie.currency_price.data.model.CurrencyInfoWrapper
import io.reactivex.Observable
import retrofit2.http.GET

interface CurrencyApi {

	@GET("rates")
	fun getCurrencyData(): Observable<CurrencyInfoWrapper>

}