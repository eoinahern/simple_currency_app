package currencyprice.eoinahern.ie.currency_price.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import currencyprice.eoinahern.ie.currency_price.data.model.CurrencyInfo


@Dao
interface CurrencyDao {

	@Insert(onConflict = REPLACE)
	fun saveList(currencyList: List<CurrencyInfo>)

	@Query("SELECT * FROM CurrencyInfo")
	fun getAll(): List<CurrencyInfo>

	@Query("SELECT COUNT(*) FROM CurrencyInfo")
	fun getSize(): Int
}