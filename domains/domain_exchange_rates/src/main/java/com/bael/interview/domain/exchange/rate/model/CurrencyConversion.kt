package com.bael.interview.domain.exchange.rate.model

import java.io.Serializable

/**
 * Created by ErickSumargo on 01/04/21.
 */

data class CurrencyConversion(
    val target: Currency,
    val rate: Double,
    val amount: Double,
    val source: Currency,
) : Serializable
