package com.bael.interview.domain.exchange.rate.usecase

import com.bael.interview.domain.common.response.Response
import com.bael.interview.domain.common.response.Response.Empty
import com.bael.interview.domain.common.response.Response.Loading
import com.bael.interview.domain.common.response.Response.Success
import com.bael.interview.domain.exchange.rate.mapper.CurrencyMapper
import com.bael.interview.domain.exchange.rate.model.Currency
import com.bael.interview.domain.exchange.rate.model.CurrencyConversion
import com.bael.interview.lib.database.repository.ExchangeRateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class DefaultLoadCurrencyConversionUseCase @Inject constructor(
    private val exchangeRateRepository: ExchangeRateRepository,
    private val convertCurrencyUseCase: ConvertCurrencyUseCase,
    private val currencyMapper: CurrencyMapper
) : LoadCurrencyConversionUseCase {

    override fun invoke(
        amount: Double,
        source: Currency,
        page: Int,
        limit: Int
    ): Flow<Response<List<CurrencyConversion>>> {
        return flow {
            emit(Loading)

            val conversions = loadCurrencyConversion(amount, source, page, limit)
            if (conversions.isEmpty()) {
                emit(Empty)
            } else {
                emit(Success(data = conversions))
            }
        }
    }

    private suspend fun loadCurrencyConversion(
        amount: Double,
        source: Currency,
        page: Int,
        limit: Int
    ): List<CurrencyConversion> {
        return exchangeRateRepository.loadExchangeRates(page, limit)
            .map { exchangeRate ->
                currencyMapper.map(exchangeRate)
            }.flatMap { target ->
                convertCurrencyUseCase(amount, source, target)
                    .map { response ->
                        if (response is Success) response.data
                        else null
                    }
                    .filterNotNull()
                    .toList()
            }
    }
}
