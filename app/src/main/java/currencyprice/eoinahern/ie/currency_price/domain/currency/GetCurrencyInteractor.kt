package currencyprice.eoinahern.ie.currency_price.domain.currency

import currencyprice.eoinahern.ie.currency_price.data.api.CurrencyApi
import currencyprice.eoinahern.ie.currency_price.data.database.CurrencyDao
import currencyprice.eoinahern.ie.currency_price.data.model.CurrencyInfo
import currencyprice.eoinahern.ie.currency_price.data.model.setIsHigherOrEqual
import currencyprice.eoinahern.ie.currency_price.di.scope.PerScreen
import currencyprice.eoinahern.ie.currency_price.domain.base.BaseInteractor
import currencyprice.eoinahern.ie.currency_price.tools.mapIsHigher
import io.reactivex.Observable
import javax.inject.Inject

@PerScreen
class GetCurrencyInteractor @Inject constructor(private val currencyApi: CurrencyApi,
												private val currencyDao: CurrencyDao) : BaseInteractor<List<CurrencyInfo>>() {

	override fun buildObservable(): Observable<List<CurrencyInfo>> = currencyApi.getCurrencyData()
			.map {

				if (currencyDao.getSize() != 0) {
					val oldList = currencyDao.getAll()
					it.rates.mapIsHigher(oldList)
				} else {
					it.rates
				}
			}
			.doAfterNext { list ->
				currencyDao.saveList(list)
			}
}
