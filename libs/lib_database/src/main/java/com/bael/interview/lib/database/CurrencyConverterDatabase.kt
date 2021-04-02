package com.bael.interview.lib.database

import com.bael.interview.lib.database.dao.ExchangeRateDao

/**
 * Created by ErickSumargo on 01/04/21.
 */

interface CurrencyConverterDatabase {
    val exchangeRateDao: ExchangeRateDao
}
