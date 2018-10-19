package currencyprice.eoinahern.ie.currency_price.domain.currency

import currencyprice.eoinahern.ie.currency_price.data.api.CurrencyApi
import currencyprice.eoinahern.ie.currency_price.data.model.CurrencyInfo
import currencyprice.eoinahern.ie.currency_price.di.scope.PerScreen
import currencyprice.eoinahern.ie.currency_price.domain.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

@PerScreen
class GetCurrencyInteractor @Inject constructor(private val currencyApi: CurrencyApi) : BaseInteractor<List<CurrencyInfo>>() {

	override fun buildObservable(): Observable<List<CurrencyInfo>> = currencyApi.getCurrencyData().map { it.rates }
}