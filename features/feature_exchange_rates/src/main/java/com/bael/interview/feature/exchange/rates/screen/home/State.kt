package com.bael.interview.feature.exchange.rates.screen.home

import com.bael.interview.domain.common.response.Response
import com.bael.interview.domain.exchange.rate.model.CurrencyConversion
import com.bael.interview.domain.exchange.rate.model.CurrencySymbol
import com.bael.interview.lib.presentation.state.BaseState

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal data class State(
    val amount: Double,
    val source: String,
    val currencySymbol: Response<List<CurrencySymbol>>,
    val currencyConversion: Response<CurrencyConversion>
) : BaseState()
