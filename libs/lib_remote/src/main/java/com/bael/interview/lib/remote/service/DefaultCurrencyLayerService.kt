package com.bael.interview.lib.remote.service

import com.bael.interview.lib.remote.BuildConfig.API_KEY
import com.bael.interview.lib.remote.response.ExchangeRatesResponse
import com.bael.interview.lib.remote.rest.CurrencyLayerREST
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

class DefaultCurrencyLayerService @Inject constructor(
    private val rest: CurrencyLayerREST
) : BaseService(),
    CurrencyLayerService {

    override suspend fun fetchExchangeRates(): Result<ExchangeRatesResponse> {
        return request {
            rest.fetchExchangeRates(accessKey = API_KEY)
        }
    }
}