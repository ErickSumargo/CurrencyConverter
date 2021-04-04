package com.bael.interview.domain.exchange.rate.mapper

import com.bael.interview.domain.common.mapper.Mapper
import com.bael.interview.domain.exchange.rate.model.Currency
import com.bael.interview.lib.database.entity.ExchangeRate
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class CurrencyMapper @Inject constructor() : Mapper<ExchangeRate, Currency> {

    override fun map(data: ExchangeRate): Currency {
        return Currency(
            id = data.id
        )
    }
}
