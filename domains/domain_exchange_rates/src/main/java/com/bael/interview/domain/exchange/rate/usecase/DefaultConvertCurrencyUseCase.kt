package com.bael.interview.domain.exchange.rate.usecase

import com.bael.interview.domain.common.mapper.Mapper
import com.bael.interview.domain.common.response.Response
import com.bael.interview.domain.common.response.Response.Error
import com.bael.interview.domain.common.response.Response.Loading
import com.bael.interview.domain.common.response.Response.Success
import com.bael.interview.domain.exchange.rate.model.CurrencyConversion
import com.bael.interview.lib.database.model.CurrencyRate
import com.bael.interview.lib.database.repository.ExchangeRateRepository
import com.bael.interview.lib.remote.response.ExchangeRatesResponse
import com.bael.interview.lib.remote.service.CurrencyLayerService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class DefaultConvertCurrencyUseCase @Inject constructor(
    private val currencyLayerService: CurrencyLayerService,
    private val exchangeRateRepository: ExchangeRateRepository,
    private val currencyRateMapper: Mapper<ExchangeRatesResponse, CurrencyRate>
) : ConvertCurrencyUseCase {

    override fun invoke(amount: Double, source: String): Flow<Response<CurrencyConversion>> {
        return flow {
            emit(Loading)

            currencyLayerService.fetchExchangeRates()
                .fold(
                    onSuccess = { response ->
                        insertExchangeRates(response)
                        emit(Success(data = convertCurrency(amount, source)))
                    },
                    onFailure = { error ->
                        emit(Error(error as Exception))
                    }
                )
        }
    }

    private suspend fun insertExchangeRates(response: ExchangeRatesResponse) {
        exchangeRateRepository.insertExchangeRates(
            exchangeRates = currencyRateMapper.map(response).rates
        )
    }

    private suspend fun convertCurrency(amount: Double, source: String): CurrencyConversion {
        val currencies = exchangeRateRepository.loadExchangeRates()
            .map { exchangeRate ->
                exchangeRate.id to exchangeRate.rate
            }.toMap()
        val sourceRate = currencies[source] ?: 1.0
        val rates = currencies.map { (symbol, rate) ->
            symbol to (rate / sourceRate * amount)
        }

        return CurrencyConversion(
            source = source to amount,
            rates = rates
        )
    }
}
