package currencyprice.eoinahern.ie.currency_price

import android.app.Activity
import android.app.Application
import currencyprice.eoinahern.ie.currency_price.di.component.DaggerAppComponent
import currencyprice.eoinahern.ie.currency_price.di.module.AppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class CurrencyApp : Application(), HasActivityInjector {

	@Inject
	lateinit var injector: DispatchingAndroidInjector<Activity>

	override fun onCreate() {
		super.onCreate()
		DaggerAppComponent.builder().appModule(AppModule(this)).build().inject(this)
	}

	override fun activityInjector(): AndroidInjector<Activity> {
		return injector
	}


}