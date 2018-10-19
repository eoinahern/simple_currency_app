package currencyprice.eoinahern.ie.currency_price.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import currencyprice.eoinahern.ie.currency_price.di.scope.PerScreen
import javax.inject.Inject

@PerScreen
class CurrencyViewModelFactory @Inject constructor(private val currencyViewModel: CurrencyViewModel) : ViewModelProvider.Factory {

	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(CurrencyViewModel::class.java)) {
			return currencyViewModel as T
		}

		throw IllegalAccessException("unknown viewmodel class")
	}

}