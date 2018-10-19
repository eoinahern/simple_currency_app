package currencyprice.eoinahern.ie.currency_price.di.module

import currencyprice.eoinahern.ie.currency_price.di.scope.PerScreen
import currencyprice.eoinahern.ie.currency_price.ui.CurrencyActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class BuilderModule {

	@PerScreen
	@ContributesAndroidInjector
	abstract fun CurrencyActivity(): CurrencyActivity

}