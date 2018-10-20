package currencyprice.eoinahern.ie.currency_price.domain.base

import io.reactivex.Observer
import io.reactivex.disposables.Disposable


abstract class BaseObserver<T> : Observer<T> {

	override fun onComplete() {
		println("on completed called")
	}

	override fun onSubscribe(d: Disposable) {
		//do nothing
	}

}