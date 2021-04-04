package com.bael.interview.lib.database.repository

import com.bael.interview.lib.database.CurrencyConverterDatabase
import com.bael.interview.lib.database.entity.ExchangeRate
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class DefaultExchangeRateRepository @Inject constructor(
    private val database: CurrencyConverterDatabase
) : ExchangeRateRepository {

    override suspend fun insertExchangeRates(exchangeRates: List<ExchangeRate>) {
        database.exchangeRateDao
            .insertExchangeRates(exchangeRates)
    }

    override suspend fun loadExchangeRates(): List<ExchangeRate> {
        return database.exchangeRateDao
            .loadExchangeRates()
    }

    override suspend fun loadExchangeRates(page: Int, limit: Int): List<ExchangeRate> {
        return database.exchangeRateDao
            .loadExchangeRates(page, limit)
    }

    override suspend fun loadExchangeRate(currency: String): ExchangeRate {
        return database.exchangeRateDao
            .loadExchangeRate(currency)
    }
}
