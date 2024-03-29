package com.bael.interview.lib.database.test

import android.content.Context
import androidx.room.Room.inMemoryDatabaseBuilder
import com.bael.interview.lib.database.CurrencyConverterDatabase
import com.bael.interview.lib.database.CurrencyConverterRoomDatabase
import com.bael.interview.lib.database.dao.ExchangeRateDao
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created by ErickSumargo on 04/04/21.
 */

internal class FakeCurrencyConverterDatabase @Inject constructor(
    @ApplicationContext context: Context
) : CurrencyConverterDatabase {
    private val database: CurrencyConverterRoomDatabase by lazy {
        inMemoryDatabaseBuilder(context, CurrencyConverterRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    override val exchangeRateDao: ExchangeRateDao
        get() = database.exchangeRateDao

    override fun closeConnection() {
        database.close()
    }
}
