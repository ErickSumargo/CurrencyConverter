package com.bael.interview.lib.remote.rest

import com.bael.interview.lib.remote.BuildConfig.API_KEY
import com.bael.interview.lib.remote.response.ExchangeRatesResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by ErickSumargo on 04/04/21.
 */

interface CurrencyLayerREST {

    @GET("live?access_key=$API_KEY")
    suspend fun fetchExchangeRates(): Response<ExchangeRatesResponse>
}
