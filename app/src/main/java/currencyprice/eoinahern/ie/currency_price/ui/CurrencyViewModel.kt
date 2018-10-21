package currencyprice.eoinahern.ie.currency_price.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import currencyprice.eoinahern.ie.currency_price.data.model.CurrencyInfo
import currencyprice.eoinahern.ie.currency_price.di.scope.PerScreen
import currencyprice.eoinahern.ie.currency_price.domain.base.BaseObserver
import currencyprice.eoinahern.ie.currency_price.domain.currency.GetCurrencyInteractor
import currencyprice.eoinahern.ie.currency_price.domain.currency.GetCurrencyRepeatInteractor
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@PerScreen
class CurrencyViewModel @Inject constructor(private val getCurrencyInteractor: GetCurrencyInteractor,
											private val getCurrencyRepeatInteractor: GetCurrencyRepeatInteractor) : ViewModel() {

	private var currencyList: MutableLiveData<List<CurrencyInfo>> = MutableLiveData()
	private var errorStr: MutableLiveData<String> = MutableLiveData()

	fun currencyList(): LiveData<List<CurrencyInfo>> = currencyList

	fun currencyError(): LiveData<String> = errorStr

	fun updateCurrencyData() {

		getCurrencyInteractor.execute(object : BaseObserver<List<CurrencyInfo>>() {

			override fun onSubscribe(d: Disposable) {
				getCurrencyInteractor.addToCompositDisposable(d)
			}

			override fun onNext(list: List<CurrencyInfo>) {
				currencyList.postValue(list)
				executeRepeating()
			}

			override fun onError(e: Throwable) {
				errorStr.postValue(e.message)
			}
		})
	}

	private fun executeRepeating() {

		getCurrencyRepeatInteractor.execute(object : BaseObserver<List<CurrencyInfo>>() {
			override fun onNext(list: List<CurrencyInfo>) {
				currencyList.value = list
			}

			override fun onError(e: Throwable) {
				Log.d("error loading", "error")
			}

			override fun onSubscribe(d: Disposable) {
				getCurrencyRepeatInteractor.addToCompositDisposable(d)
			}
		})
	}

	fun unsubscribe() {
		getCurrencyInteractor.unsubscribe()
		getCurrencyRepeatInteractor.unsubscribe()
	}
}