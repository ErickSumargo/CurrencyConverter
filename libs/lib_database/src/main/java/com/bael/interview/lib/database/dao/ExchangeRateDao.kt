package com.bael.interview.lib.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.bael.interview.lib.database.entity.ExchangeRate

/**
 * Created by ErickSumargo on 01/04/21.
 */

@Dao
interface ExchangeRateDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertExchangeRates(exchangeRates: List<ExchangeRate>)

    @Query("SELECT * FROM exchange_rate")
    suspend fun loadExchangeRates(): List<ExchangeRate>
}