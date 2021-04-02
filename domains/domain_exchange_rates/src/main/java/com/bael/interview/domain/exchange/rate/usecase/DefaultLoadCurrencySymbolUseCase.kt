package com.bael.interview.domain.exchange.rate.usecase

import com.bael.interview.domain.common.mapper.ListMapper
import com.bael.interview.domain.common.mapper.Mapper
import com.bael.interview.domain.common.response.Response
import com.bael.interview.domain.common.response.Response.Error
import com.bael.interview.domain.common.response.Response.Loading
import com.bael.interview.domain.common.response.Response.Success
import com.bael.interview.domain.exchange.rate.model.CurrencySymbol
import com.bael.interview.lib.database.entity.ExchangeRate
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

internal class DefaultLoadCurrencySymbolUseCase @Inject constructor(
    private val currencyLayerService: CurrencyLayerService,
    private val exchangeRateRepository: ExchangeRateRepository,
    private val currencySymbolMapper: ListMapper<ExchangeRate, CurrencySymbol>,
    private val currencyRateMapper: Mapper<ExchangeRatesResponse, CurrencyRate>
) : LoadCurrencySymbolUseCase {

    override fun invoke(): Flow<Response<List<CurrencySymbol>>> {
        return flow {
            emit(Loading)

            currencyLayerService.fetchExchangeRates()
                .fold(
                    onSuccess = { response ->
                        storeExchangeRates(response)
                        emit(Success(data = loadCurrencySymbol()))
                    },
                    onFailure = { error ->
                        emit(Error(error as Exception))
                    }
                )
        }
    }

    private suspend fun storeExchangeRates(response: ExchangeRatesResponse) {
        exchangeRateRepository.insertExchangeRates(
            exchangeRates = currencyRateMapper.map(response).rates
        )
    }

    private suspend fun loadCurrencySymbol(): List<CurrencySymbol> {
        val exchangeRates = exchangeRateRepository.loadExchangeRates()
        return currencySymbolMapper.map(exchangeRates)
    }
}
