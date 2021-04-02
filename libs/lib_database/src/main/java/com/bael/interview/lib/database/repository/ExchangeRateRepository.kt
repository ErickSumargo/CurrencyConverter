package com.bael.interview.lib.database.repository

import com.bael.interview.lib.database.entity.ExchangeRate

/**
 * Created by ErickSumargo on 01/04/21.
 */

interface ExchangeRateRepository {

    suspend fun insertExchangeRates(exchangeRates: List<ExchangeRate>)

    suspend fun loadExchangeRates(): List<ExchangeRate>
}