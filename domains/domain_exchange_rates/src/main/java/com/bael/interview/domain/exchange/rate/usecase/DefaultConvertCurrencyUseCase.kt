package com.bael.interview.domain.exchange.rate.usecase

import com.bael.interview.domain.common.response.Response
import com.bael.interview.domain.common.response.Response.Loading
import com.bael.interview.domain.common.response.Response.Success
import com.bael.interview.domain.exchange.rate.model.Currency
import com.bael.interview.domain.exchange.rate.model.CurrencyConversion
import com.bael.interview.lib.database.repository.ExchangeRateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class DefaultConvertCurrencyUseCase @Inject constructor(
    private val exchangeRateRepository: ExchangeRateRepository
) : ConvertCurrencyUseCase {

    override fun invoke(
        amount: Double,
        source: Currency,
        target: Currency
    ): Flow<Response<CurrencyConversion>> {
        return flow {
            emit(Loading)
            emit(Success(data = convertCurrency(amount, source, target)))
        }
    }

    private suspend fun convertCurrency(
        amount: Double,
        source: Currency,
        target: Currency
    ): CurrencyConversion {
        val sourceRate = exchangeRateRepository.loadExchangeRate(currency = source.id)
        val targetRate = exchangeRateRepository.loadExchangeRate(currency = target.id)

        return CurrencyConversion(
            target = target,
            rate = (targetRate.rate / sourceRate.rate) * amount,
            source = source,
            amount = amount
        )
    }
}
