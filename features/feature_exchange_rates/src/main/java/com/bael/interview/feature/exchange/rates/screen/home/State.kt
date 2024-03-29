package com.bael.interview.feature.exchange.rates.screen.home

import com.bael.interview.domain.common.response.Response
import com.bael.interview.domain.exchange.rate.model.Currency
import com.bael.interview.domain.exchange.rate.model.CurrencyConversion
import com.bael.interview.lib.presentation.state.BaseState

/**
 * Created by ErickSumargo on 04/04/21.
 */

internal data class State(
    val amount: Double,
    val source: Currency,
    val currencyResponse: Response<List<Currency>>,
    val currencyConversionResponses: List<Response<List<CurrencyConversion>>>
) : BaseState()
