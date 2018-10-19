package currencyprice.eoinahern.ie.currency_price.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import currencyprice.eoinahern.ie.currency_price.R
import currencyprice.eoinahern.ie.currency_price.data.model.CurrencyInfo
import currencyprice.eoinahern.ie.currency_price.di.scope.PerScreen
import currencyprice.eoinahern.ie.currency_price.tools.FormatHelper
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.currency_item_layout.*
import java.text.DecimalFormat
import javax.inject.Inject

@PerScreen
class CurrencyAdapter @Inject constructor(private val formatHelper: FormatHelper) : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

	private var currencyList: MutableList<CurrencyInfo> = mutableListOf()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val v = LayoutInflater.from(parent.context).inflate(R.layout.currency_item_layout, parent, false)
		return ViewHolder(v)
	}

	override fun getItemCount() = currencyList.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val currency = currencyList[position]
		holder.valueTxt.text = formatHelper.formatCurrency(currency.price)
		holder.currencyTxt.text = currency.symbol
	}

	fun setCurrencyList(currencyListIn: List<CurrencyInfo>) {

		if (!currencyList.isEmpty())
			currencyList.clear()

		currencyList.addAll(currencyListIn)
		notifyItemRangeInserted(0, currencyListIn.size)
	}

	inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer
}