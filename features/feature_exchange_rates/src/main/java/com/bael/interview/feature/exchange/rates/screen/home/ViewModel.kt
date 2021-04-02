package com.bael.interview.feature.exchange.rates.screen.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bael.interview.domain.common.response.Response
import com.bael.interview.domain.exchange.rate.model.CurrencyConversion
import com.bael.interview.domain.exchange.rate.model.CurrencySymbol
import com.bael.interview.domain.exchange.rate.usecase.ConvertCurrencyUseCase
import com.bael.interview.domain.exchange.rate.usecase.LoadCurrencySymbolUseCase
import com.bael.interview.lib.presentation.ext.reduce
import com.bael.interview.lib.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

@HiltViewModel
internal class ViewModel @Inject constructor(
    initState: State,
    savedStateHandle: SavedStateHandle,
    private val loadCurrencySymbolUseCase: LoadCurrencySymbolUseCase,
    private val convertCurrencyUseCase: ConvertCurrencyUseCase
) : BaseViewModel<State, Event>(initState, savedStateHandle) {

    fun loadCurrencySymbol() {
        loadCurrencySymbolUseCase()
            .flowOn(context = thread.io)
            .onEach(::renderLoadCurrencySymbolResponse)
            .flowOn(context = thread.default)
            .launchIn(scope = viewModelScope)
    }

    fun convertCurrency(amount: Double, source: String) {
        convertCurrencyUseCase(amount, source)
            .flowOn(context = thread.io)
            .onEach(::renderConvertCurrencyResponse)
            .flowOn(context = thread.default)
            .launchIn(scope = viewModelScope)
    }

    fun changeSourceCurrency(source: String) {
        val newState = state.reduce {
            copy(source = source)
        }
        render(newState)
    }

    private fun renderLoadCurrencySymbolResponse(response: Response<List<CurrencySymbol>>) {
        val newState = state.reduce {
            copy(currencySymbol = response)
        }
        render(newState)
    }

    private fun renderConvertCurrencyResponse(response: Response<CurrencyConversion>) {
        val newState = state.reduce {
            copy(currencyConversion = response)
        }
        render(newState)
    }
}
