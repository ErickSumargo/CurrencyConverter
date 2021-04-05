package com.bael.interview.feature.exchange.rates.screen.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bael.interview.domain.common.ext.findSuccess
import com.bael.interview.domain.common.response.Response
import com.bael.interview.domain.common.response.Response.Empty
import com.bael.interview.domain.common.response.Response.Error
import com.bael.interview.domain.common.response.Response.Loading
import com.bael.interview.domain.common.response.Response.Success
import com.bael.interview.domain.exchange.rate.model.Currency
import com.bael.interview.domain.exchange.rate.model.CurrencyConversion
import com.bael.interview.domain.exchange.rate.usecase.LoadCurrencyConversionUseCase
import com.bael.interview.domain.exchange.rate.usecase.LoadCurrencyUseCase
import com.bael.interview.feature.exchange.rates.screen.home.Event.DisplayCurrencyError
import com.bael.interview.feature.exchange.rates.screen.home.Event.ReloadCurrency
import com.bael.interview.lib.presentation.ext.reduce
import com.bael.interview.lib.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by ErickSumargo on 04/04/21.
 */

@HiltViewModel
internal class ViewModel @Inject constructor(
    initState: State,
    savedStateHandle: SavedStateHandle,
    private val loadCurrencyUseCase: LoadCurrencyUseCase,
    private val loadCurrencyConversionUseCase: LoadCurrencyConversionUseCase
) : BaseViewModel<State, Event>(initState, savedStateHandle) {
    private var loadCurrencyJob: Job? = null

    private var loadCurrencyConversionJob: Job? = null

    fun loadCurrency() {
        loadCurrencyJob?.cancel()
        loadCurrencyJob = loadCurrencyUseCase()
            .flowOn(context = thread.io)
            .onEach(::renderLoadCurrencyResponse)
            .flowOn(context = thread.default)
            .launchIn(scope = viewModelScope)
    }

    fun loadCurrencyConversion(amount: Double, source: String, page: Int, limit: Int) {
        loadCurrencyConversionJob?.cancel()
        loadCurrencyConversionJob =
            loadCurrencyConversionUseCase(amount, source = Currency(id = source), page, limit)
                .flowOn(context = thread.io)
                .onEach { response ->
                    renderLoadCurrencyConversionResponse(response, page)
                }
                .flowOn(context = thread.default)
                .launchIn(scope = viewModelScope)
    }

    fun updateAmount(amount: Double) {
        val newState = state.reduce {
            copy(amount = amount)
        }
        render(newState)
    }

    fun changeSourceCurrency(source: String) {
        val newState = state.reduce {
            copy(source = Currency(id = source))
        }
        render(newState)
    }

    fun clearCurrencyConversion() {
        val newState = state.reduce {
            copy(currencyConversionResponses = listOf())
        }
        render(newState)
    }

    private fun renderLoadCurrencyResponse(response: Response<List<Currency>>) {
        val newState = state.reduce {
            when (response) {
                is Error -> {
                    val newEvent = DisplayCurrencyError(message = response.error.message.orEmpty())
                    action(newEvent)
                }
                is Success -> {
                    val newEvent = ReloadCurrency()
                    action(newEvent)
                }
            }
            copy(currencyResponse = response)
        }
        render(newState)
    }

    private fun renderLoadCurrencyConversionResponse(
        response: Response<List<CurrencyConversion>>,
        page: Int
    ) {
        val newState = state.reduce {
            when (response) {
                is Loading, is Error, is Empty -> copy(
                    currencyConversionResponses = listOf(response).takeIf {
                        page == 1
                    } ?: currencyConversionResponses.dropLastWhile {
                        it is Loading || it is Error || it is Empty
                    } + response
                )
                is Success -> copy(
                    currencyConversionResponses = listOf(response).takeIf {
                        page == 1
                    } ?: listOf(
                        Success(data = currencyConversionResponses.findSuccess()?.data.orEmpty() + response.data)
                    )
                )
            }
        }
        render(newState)
    }
}
