package com.bael.interview.domain.exchange.rate.mapper

import com.bael.interview.domain.common.mapper.Mapper
import com.bael.interview.lib.database.entity.ExchangeRate
import com.bael.interview.lib.database.model.CurrencyRate
import com.bael.interview.lib.remote.response.ExchangeRatesResponse
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class CurrencyRateMapper @Inject constructor() :
    Mapper<ExchangeRatesResponse, CurrencyRate> {

    override fun map(data: ExchangeRatesResponse): CurrencyRate {
        return CurrencyRate(
            rates = listOf(
                ExchangeRate(
                    id = "AED",
                    rate = data.quotes.USDAED,
                    timestamp = data.timestamp
                ),
                ExchangeRate(
                    id = "AFN",
                    rate = data.quotes.USDAFN,
                    timestamp = data.timestamp
                ),
                ExchangeRate(
                    id = "ALL",
                    rate = data.quotes.USDALL,
                    timestamp = data.timestamp
                )
            )
        )
    }
}
