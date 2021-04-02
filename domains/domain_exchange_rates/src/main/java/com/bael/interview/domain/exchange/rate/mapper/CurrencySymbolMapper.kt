package com.bael.interview.domain.exchange.rate.mapper

import com.bael.interview.domain.common.mapper.Mapper
import com.bael.interview.domain.exchange.rate.model.CurrencySymbol
import com.bael.interview.lib.database.entity.ExchangeRate
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class CurrencySymbolMapper @Inject constructor() : Mapper<ExchangeRate, CurrencySymbol> {

    override fun map(data: ExchangeRate): CurrencySymbol {
        return CurrencySymbol(
            symbol = data.id
        )
    }
}
