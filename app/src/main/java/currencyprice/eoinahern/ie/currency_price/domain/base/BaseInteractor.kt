package currencyprice.eoinahern.ie.currency_price.domain.base

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


 abstract class BaseInteractor<T> {

	private var disposables: CompositeDisposable = CompositeDisposable()

	fun execute(obs: Observer<T>, ioScheduler: Scheduler = Schedulers.io(),
				mainScheduler: Scheduler = AndroidSchedulers.mainThread()) {

		buildObservable()
				.subscribeOn(ioScheduler)
				.observeOn(mainScheduler)
				.subscribe(obs)
	}

	abstract fun buildObservable(): Observable<T>

	fun addToCompositDisposable(disposable: Disposable) {
		disposables.add(disposable)
	}

	fun unsubscribe() {
		disposables.clear()
	}
}