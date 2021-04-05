package com.bael.interview.domain.exchange.rate.usecase

import com.bael.interview.domain.common.response.Response
import com.bael.interview.domain.exchange.rate.model.Currency
import com.bael.interview.domain.exchange.rate.model.CurrencyConversion
import kotlinx.coroutines.flow.Flow

/**
 * Created by ErickSumargo on 04/04/21.
 */

interface ConvertCurrencyUseCase {

    operator fun invoke(
        amount: Double,
        source: Currency,
        target: Currency
    ): Flow<Response<CurrencyConversion>>
}
