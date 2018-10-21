package currencyprice.eoinahern.ie.currency_price.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import currencyprice.eoinahern.ie.currency_price.data.model.CurrencyInfo

@Database(entities = [CurrencyInfo::class], version = 1, exportSchema = false)
abstract class CurrencyDatabase : RoomDatabase() {
	abstract fun CurrencyDao(): CurrencyDao
}