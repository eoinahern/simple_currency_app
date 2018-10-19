package currencyprice.eoinahern.ie.currency_price.di.component

import currencyprice.eoinahern.ie.currency_price.CurrencyApp
import currencyprice.eoinahern.ie.currency_price.di.module.AppModule
import currencyprice.eoinahern.ie.currency_price.di.module.BuilderModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, BuilderModule::class, AppModule::class])
interface AppComponent {

	fun inject(app: CurrencyApp)
}