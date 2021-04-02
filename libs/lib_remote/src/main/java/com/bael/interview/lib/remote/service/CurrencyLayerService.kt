package com.bael.interview.lib.remote.service

import com.bael.interview.lib.remote.response.ExchangeRatesResponse

/**
 * Created by ErickSumargo on 01/04/21.
 */

interface CurrencyLayerService {

    suspend fun fetchExchangeRates(): Result<ExchangeRatesResponse>
}
