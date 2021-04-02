package com.bael.interview.domain.exchange.rate.model

import java.io.Serializable

/**
 * Created by ErickSumargo on 01/04/21.
 */

data class CurrencyConversion(
    val source: Pair<String, Double>,
    val rates: List<Pair<String, Double>>
) : Serializable
