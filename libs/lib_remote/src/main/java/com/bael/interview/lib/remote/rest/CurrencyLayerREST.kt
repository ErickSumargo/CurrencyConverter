package com.bael.interview.lib.remote.rest

import com.bael.interview.lib.remote.response.ExchangeRatesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ErickSumargo on 01/04/21.
 */

interface CurrencyLayerREST {

    @GET("live")
    suspend fun fetchExchangeRates(
        @Query("access_key") accessKey: String
    ): Response<ExchangeRatesResponse>
}
