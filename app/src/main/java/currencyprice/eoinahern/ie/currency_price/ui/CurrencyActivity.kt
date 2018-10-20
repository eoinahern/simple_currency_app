package currencyprice.eoinahern.ie.currency_price.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import currencyprice.eoinahern.ie.currency_price.R
import currencyprice.eoinahern.ie.currency_price.data.model.CurrencyInfo
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_currency.*
import javax.inject.Inject

class CurrencyActivity : AppCompatActivity() {

	@Inject
	lateinit var viewModelFactory: CurrencyViewModelFactory

	@Inject
	lateinit var adapter: CurrencyAdapter

	lateinit var viewModel: CurrencyViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		AndroidInjection.inject(this)
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_currency)
		setupToolbar()
		setupRecycler()
		initViewModel()
		viewModel.updateCurrencyData()
	}

	private fun setupToolbar() {
		setSupportActionBar(toolbar)
		supportActionBar?.setTitle(R.string.title_currency)
	}

	private fun initViewModel() {

		viewModel = ViewModelProviders.of(this, viewModelFactory).get(CurrencyViewModel::class.java)

		viewModel.currencyList().observe(this,
				Observer<List<CurrencyInfo>> { currencyList ->
					adapter.setCurrencyList(currencyList)
				})

		viewModel.currencyError().observe(this,
				Observer<String> {
					println(it)
				})
	}

	private fun setupRecycler() {
		recycler.layoutManager = LinearLayoutManager(this)
		recycler.adapter = adapter
	}


	private fun showLoading() {

	}

	private fun hideLoading() {

	}

	private fun showError() {

	}

	override fun onDestroy() {
		super.onDestroy()
		viewModel.unsubscribe()
	}
}
