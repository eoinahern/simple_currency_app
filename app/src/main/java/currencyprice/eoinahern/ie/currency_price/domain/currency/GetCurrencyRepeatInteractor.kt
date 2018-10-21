package currencyprice.eoinahern.ie.currency_price.domain.currency

import currencyprice.eoinahern.ie.currency_price.data.api.CurrencyApi
import currencyprice.eoinahern.ie.currency_price.data.database.CurrencyDao
import currencyprice.eoinahern.ie.currency_price.data.model.CurrencyInfo
import currencyprice.eoinahern.ie.currency_price.di.scope.PerScreen
import currencyprice.eoinahern.ie.currency_price.domain.base.BaseInteractor
import currencyprice.eoinahern.ie.currency_price.tools.mapIsHigher
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@PerScreen
class GetCurrencyRepeatInteractor @Inject constructor(private val currencyApi: CurrencyApi,
													  private val currencyDao: CurrencyDao) : BaseInteractor<List<CurrencyInfo>>() {

	override fun buildObservable(): Observable<List<CurrencyInfo>> {
		return currencyApi.getCurrencyData().map {
			val oldList = currencyDao.getAll()
			it.rates.mapIsHigher(oldList)
		}.delay(5, TimeUnit.SECONDS).repeatWhen { completed ->
			completed.delay(5, TimeUnit.SECONDS)
		}.doAfterNext { currencyDao.saveList(it) }
	}


}