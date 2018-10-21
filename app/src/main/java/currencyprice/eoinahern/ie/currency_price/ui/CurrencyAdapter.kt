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

	private var newCurrencyList: MutableList<CurrencyInfo> = mutableListOf()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val v = LayoutInflater.from(parent.context).inflate(R.layout.currency_item_layout, parent, false)
		return ViewHolder(v, formatHelper)
	}

	override fun getItemCount() = newCurrencyList.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val newCurrency = newCurrencyList[position]
		holder.bindData(newCurrency)
	}

	fun setCurrencyList(currencyListIn: List<CurrencyInfo>) {

		if (!newCurrencyList.isEmpty())
			newCurrencyList.clear()

		newCurrencyList.addAll(currencyListIn)
		notifyDataSetChanged()
	}

	inner class ViewHolder(override val containerView: View, private val formatHelper: FormatHelper) : RecyclerView.ViewHolder(containerView), LayoutContainer {

		fun bindData(newCurrencyInfo: CurrencyInfo) {

			valueTxt.text = formatHelper.formatCurrency(newCurrencyInfo.price)
			currencyTxt.text = newCurrencyInfo.symbol

			if (newCurrencyInfo.isHigherOrEqual) {
				valueTxt.setTextColor(containerView.context.getColor(R.color.colorHigher))
			} else {
				valueTxt.setTextColor(containerView.context.getColor(R.color.colorLower))
			}
		}
	}
}