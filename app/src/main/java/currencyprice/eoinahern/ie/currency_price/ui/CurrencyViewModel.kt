package currencyprice.eoinahern.ie.currency_price.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import currencyprice.eoinahern.ie.currency_price.data.model.CurrencyInfo
import currencyprice.eoinahern.ie.currency_price.di.scope.PerScreen
import currencyprice.eoinahern.ie.currency_price.domain.currency.GetCurrencyInteractor
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@PerScreen
class CurrencyViewModel @Inject constructor(private val getCurrencyInteractor: GetCurrencyInteractor) : ViewModel() {

	private var currencyList: MutableLiveData<List<CurrencyInfo>> = MutableLiveData()
	private var errorStr: MutableLiveData<String> = MutableLiveData()

	fun currencyList(): LiveData<List<CurrencyInfo>> = currencyList

	fun currencyError(): LiveData<String> = errorStr

	//initial call
	fun initGetCurrency() {

	}

	//update while activity open
	fun updateCurrencyData() {


		getCurrencyInteractor.execute(object : Observer<List<CurrencyInfo>> {
			override fun onComplete() {
			}

			override fun onSubscribe(d: Disposable) {
				getCurrencyInteractor.addToCompositDisposable(d)
			}

			override fun onNext(list: List<CurrencyInfo>) {
				currencyList.postValue(list)
			}

			override fun onError(e: Throwable) {
				errorStr.postValue(e.message)
			}
		})
	}


	fun unsubscribe() {
		getCurrencyInteractor.unsubscribe()
	}
}