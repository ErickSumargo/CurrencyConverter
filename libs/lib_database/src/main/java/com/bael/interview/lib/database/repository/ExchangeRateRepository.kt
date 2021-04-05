package com.bael.interview.lib.database.repository

import com.bael.interview.lib.database.entity.ExchangeRate

/**
 * Created by ErickSumargo on 04/04/21.
 */

interface ExchangeRateRepository {

    suspend fun insertExchangeRates(exchangeRates: List<ExchangeRate>)

    suspend fun loadExchangeRates(): List<ExchangeRate>

    suspend fun loadExchangeRates(page: Int, limit: Int): List<ExchangeRate>

    suspend fun loadExchangeRate(currency: String): ExchangeRate
}
