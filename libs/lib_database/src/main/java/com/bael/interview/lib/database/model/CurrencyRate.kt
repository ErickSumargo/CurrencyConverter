package com.bael.interview.lib.database.model

import com.bael.interview.lib.database.entity.ExchangeRate

/**
 * Created by ErickSumargo on 01/04/21.
 */

data class CurrencyRate(
    val rates: List<ExchangeRate>
)
