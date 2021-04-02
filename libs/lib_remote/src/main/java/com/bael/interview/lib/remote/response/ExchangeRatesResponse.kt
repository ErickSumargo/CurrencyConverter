package com.bael.interview.lib.remote.response

import com.bael.interview.lib.remote.model.Quotes

/**
 * Created by ErickSumargo on 01/04/21.
 */

data class ExchangeRatesResponse(
    val privacy: String,
    val quotes: Quotes,
    val source: String,
    val success: Boolean,
    val terms: String,
    val timestamp: Long
)
