package currencyprice.eoinahern.ie.currency_price.domain.currency

import currencyprice.eoinahern.ie.currency_price.data.api.CurrencyApi
import currencyprice.eoinahern.ie.currency_price.data.model.CurrencyInfo
import currencyprice.eoinahern.ie.currency_price.di.scope.PerScreen
import currencyprice.eoinahern.ie.currency_price.domain.base.BaseInteractor
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@PerScreen
class GetCurrencyRepeatInteractor @Inject constructor(private val currencyApi: CurrencyApi) : BaseInteractor<List<CurrencyInfo>>() {

	override fun buildObservable(): Observable<List<CurrencyInfo>> {
		return currencyApi.getCurrencyData().map { it.rates }.delay(5, TimeUnit.SECONDS).repeatWhen { completed ->
			completed.delay(5, TimeUnit.SECONDS)
		}
	}


}