package currencyprice.eoinahern.ie.currency_price.di.module

import com.squareup.moshi.Moshi
import currencyprice.eoinahern.ie.currency_price.CurrencyApp
import currencyprice.eoinahern.ie.currency_price.data.api.CurrencyApi
import currencyprice.eoinahern.ie.currency_price.tools.API_ENDPOINT
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
class AppModule constructor(private val currencyApp: CurrencyApp) {

	@Provides
	@Singleton
	fun getContext() = currencyApp.applicationContext

	@Provides
	@Singleton
	fun getMoshi(): Moshi {
		return Moshi.Builder().build()
	}

	@Provides
	@Singleton
	fun getApi(moshi: Moshi): CurrencyApi {

		return Retrofit.Builder()
				.baseUrl(API_ENDPOINT)
				.addConverterFactory(MoshiConverterFactory.create(moshi))
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build().create(CurrencyApi::class.java)
	}

}