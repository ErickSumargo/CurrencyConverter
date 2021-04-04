package com.bael.interview.domain.exchange.rate.usecase

import com.bael.interview.domain.common.response.Response
import com.bael.interview.domain.exchange.rate.model.Currency
import com.bael.interview.domain.exchange.rate.model.CurrencyConversion
import kotlinx.coroutines.flow.Flow

/**
 * Created by ErickSumargo on 01/04/21.
 */

interface LoadCurrencyConversionUseCase {

    operator fun invoke(
        amount: Double,
        source: Currency,
        page: Int,
        limit: Int
    ): Flow<Response<List<CurrencyConversion>>>
}
