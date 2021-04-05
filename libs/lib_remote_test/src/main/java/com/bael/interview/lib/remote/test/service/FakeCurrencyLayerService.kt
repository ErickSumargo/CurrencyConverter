package com.bael.interview.lib.remote.test.service

import com.bael.interview.lib.remote.response.ExchangeRatesResponse
import com.bael.interview.lib.remote.service.CurrencyLayerService
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class FakeCurrencyLayerService @Inject constructor() :
    FakeBaseService<ExchangeRatesResponse>(),
    CurrencyLayerService {

    override suspend fun fetchExchangeRates(): Result<ExchangeRatesResponse> {
        return result
    }
}
