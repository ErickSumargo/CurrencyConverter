package com.bael.interview.domain.exchange.rate.usecase

import com.bael.interview.domain.common.mapper.Mapper
import com.bael.interview.domain.common.response.Response
import com.bael.interview.domain.common.response.Response.Error
import com.bael.interview.domain.common.response.Response.Loading
import com.bael.interview.domain.common.response.Response.Success
import com.bael.interview.domain.exchange.rate.model.Currency
import com.bael.interview.lib.database.entity.ExchangeRate
import com.bael.interview.lib.database.model.CurrencyRate
import com.bael.interview.lib.database.repository.ExchangeRateRepository
import com.bael.interview.lib.remote.response.ExchangeRatesResponse
import com.bael.interview.lib.remote.service.CurrencyLayerService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class DefaultLoadCurrencyUseCase @Inject constructor(
    private val currencyLayerService: CurrencyLayerService,
    private val exchangeRateRepository: ExchangeRateRepository,
    private val currencyRateMapper: Mapper<ExchangeRatesResponse, CurrencyRate>,
    private val currencyMapper: Mapper<ExchangeRate, Currency>
) : LoadCurrencyUseCase {

    override fun invoke(): Flow<Response<List<Currency>>> {
        return flow {
            emit(Loading)

//            currencyLayerService.fetchExchangeRates()
//                .fold(
//                    onSuccess = { response ->
//                        storeExchangeRates(response)
//                        emitAll(loadCurrency())
//                    },
//                    onFailure = { error ->
//                        emit(Error(error as Exception))
//                        emitAll(loadCurrency())
//                    }
//                )
            emitAll(loadCurrency())
        }
    }

    private suspend fun storeExchangeRates(response: ExchangeRatesResponse) {
        exchangeRateRepository.insertExchangeRates(
            exchangeRates = currencyRateMapper.map(response).rates
        )
    }

    private suspend fun loadCurrency(): Flow<Response<List<Currency>>> {
        return flow {
            val currencies = loadCurrencyDB()
            if (currencies.isNotEmpty()) {
                emit(Success(data = currencies))
            }
        }
    }

    private suspend fun loadCurrencyDB(): List<Currency> {
        return exchangeRateRepository.loadExchangeRates()
            .map(currencyMapper::map)
    }
}