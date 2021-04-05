package com.bael.interview.feature.exchange.rates.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bael.interview.domain.common.response.Response.Error
import com.bael.interview.domain.common.response.Response.Loading
import com.bael.interview.domain.common.response.Response.Success
import com.bael.interview.feature.exchange.rates.R
import com.bael.interview.feature.exchange.rates.adapter.CurrencyConversionAdapter
import com.bael.interview.feature.exchange.rates.adapter.diffcallback.CurrencyConversionDiffCallback
import com.bael.interview.feature.exchange.rates.databinding.ScreenHomeBinding
import com.bael.interview.feature.exchange.rates.databinding.ScreenHomeBinding.inflate
import com.bael.interview.feature.exchange.rates.screen.home.Event.DisplayCurrencyError
import com.bael.interview.feature.exchange.rates.screen.home.Event.ReloadCurrency
import com.bael.interview.lib.preference.Preference
import com.bael.interview.lib.presentation.ext.readText
import com.bael.interview.lib.presentation.fragment.BaseFragment
import com.bael.interview.lib.presentation.widget.listener.OnSpinnerItemSelectedListener
import com.bael.interview.lib.presentation.widget.listener.OnTextChangedListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by ErickSumargo on 01/04/21.
 */

@AndroidEntryPoint
internal class UI : BaseFragment<ScreenHomeBinding, ViewModel, State, Event>() {
    @Inject
    lateinit var preference: Provider<Preference>

    override val viewModel: ViewModel by viewModels()

    private val adapter: CurrencyConversionAdapter by lazy {
        CurrencyConversionAdapter(
            diffCallback = CurrencyConversionDiffCallback(),
            itemPerPage = LOAD_CONVERSION_LIMIT,
            onNextPageListener = { page ->
                viewModel.loadCurrencyConversion(
                    amount = viewBinding.amountInput.text.toString().toDoubleOrNull() ?: 0.0,
                    source = (viewBinding.currencySpinner.selectedItem as? String).orEmpty(),
                    page = page,
                    limit = LOAD_CONVERSION_LIMIT
                )
            }
        )
    }

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ScreenHomeBinding {
        return inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        viewModel.loadCurrency()
    }

    private fun setupView() {
        viewBinding.swipeRefreshLayout.also { layout ->
            layout.setOnRefreshListener {
                layout.isRefreshing = false
                viewModel.loadCurrency()
            }
        }

        viewBinding.amountInput.also { input ->
            callbackFlow {
                val onTextChangedListener = OnTextChangedListener(::offer)

                input.addTextChangedListener(onTextChangedListener)
                awaitClose { input.removeTextChangedListener(onTextChangedListener) }
            }
                .onEach { textAmount ->
                    if (textAmount.isBlank()) {
                        viewModel.clearCurrencyConversion()
                    }
                    viewModel.updateAmount(amount = textAmount.toDoubleOrNull() ?: 0.0)
                }.filter { textAmount ->
                    textAmount.isNotBlank()
                }.debounce {
                    200L
                }.onEach { textAmount ->
                    viewModel.loadCurrencyConversion(
                        amount = textAmount.toDouble(),
                        source = (viewBinding.currencySpinner.selectedItem as? String).orEmpty(),
                        page = 1,
                        limit = LOAD_CONVERSION_LIMIT
                    )
                }
                .launchIn(scope = viewLifecycleOwner.lifecycleScope)
        }

        viewBinding.listView.also { view ->
            view.adapter = adapter
        }

        viewBinding.themeButton.also { button ->
            button.setOnClickListener {
                switchTheme()
            }
        }
    }

    override suspend fun render(state: State) {
        renderStateLayout(state)
        renderCurrencySelection(state)
        renderCurrencyConversion(state)
    }

    override suspend fun action(event: Event) {
        when (event) {
            is DisplayCurrencyError -> {
                showMessage(message = event.message)
            }
            is ReloadCurrency -> {
                if (viewBinding.amountInput.text.isNullOrBlank()) return
                if (viewBinding.currencySpinner.selectedItemId < 0) return

                viewModel.loadCurrencyConversion(
                    amount = viewBinding.amountInput.text.toString().toDouble(),
                    source = (viewBinding.currencySpinner.selectedItem as? String).orEmpty(),
                    page = 1,
                    limit = LOAD_CONVERSION_LIMIT
                )
            }
        }
    }

    private fun renderStateLayout(state: State) {
        when (state.currencyResponse) {
            is Loading -> {
                if (viewBinding.currencySpinner.adapter == null) {
                    viewBinding.loadingBar.show()
                }
                viewBinding.responseStateLayout.root.visibility = GONE
            }
            is Error -> {
                viewBinding.loadingBar.hide()

                viewBinding.responseStateLayout.also { layout ->
                    layout.descriptionText.also { view ->
                        view.text = state.currencyResponse.error.message
                    }

                    layout.actionButton.also { button ->
                        button.text = readText(R.string.try_again)
                        button.setOnClickListener {
                            viewModel.loadCurrency()
                        }
                    }

                    if (viewBinding.currencySpinner.adapter == null) {
                        layout.root.visibility = VISIBLE
                    } else {
                        layout.root.visibility = GONE
                    }
                }

                if (viewBinding.currencySpinner.adapter == null) {
                    viewBinding.amountContainer.visibility = GONE
                    viewBinding.listView.visibility = GONE
                }
            }
            else -> {
                viewBinding.loadingBar.hide()
                viewBinding.responseStateLayout.root.visibility = GONE
                viewBinding.amountContainer.visibility = VISIBLE
                viewBinding.listView.visibility = VISIBLE
            }
        }
    }

    private suspend fun renderCurrencySelection(state: State) {
        viewBinding.currencySpinner.also { spinner ->
            if (state.currencyResponse !is Success) return@also

            val currencies = state.currencyResponse.data.map { currency ->
                currency.id
            }
            val sourceIndex = currencies.indexOf(
                state.source.id.takeIf { id -> id.isNotBlank() }
                    ?: preference.get().read(key = SOURCE_CURRENCY_PREFERENCE, defaultValue = "")
            )

            val adapter = ArrayAdapter(
                requireContext(),
                R.layout.item_currency,
                currencies.toTypedArray()
            )

            spinner.adapter = adapter
            spinner.onItemSelectedListener = OnSpinnerItemSelectedListener { index ->
                val selectedCurrency = currencies[index]
                if (selectedCurrency == state.source.id) return@OnSpinnerItemSelectedListener

                saveSelectedSourceCurrency(selectedCurrency)

                viewModel.changeSourceCurrency(source = selectedCurrency)
                if (viewBinding.amountInput.text?.isNotBlank() == true) {
                    viewModel.loadCurrencyConversion(
                        amount = viewBinding.amountInput.text.toString().toDouble(),
                        source = selectedCurrency,
                        page = 1,
                        limit = LOAD_CONVERSION_LIMIT
                    )
                }
            }

            if (sourceIndex != spinner.selectedItemPosition) {
                spinner.setSelection(sourceIndex)
            }
        }
    }

    private fun saveSelectedSourceCurrency(currency: String) {
        lifecycleScope.launch {
            preference.get().write(key = SOURCE_CURRENCY_PREFERENCE, currency)
        }
    }

    private fun renderCurrencyConversion(state: State) {
        if (state.currencyConversionResponses.isEmpty()) {
            adapter.clearData()
            return
        }

        state.currencyConversionResponses.forEach { response ->
            when (response) {
                is Loading -> {
                    if (state.currencyConversionResponses.size <= 1) {
                        adapter.clearData()
                    }
                }
                is Success -> {
                    adapter.submitList(response.data)
                }
            }
        }
    }

    private fun switchTheme() {
        lifecycleScope.launch {
            val isDarkTheme = preference.get().read(key = DARK_THEME_PREFERENCE, false)
            preference.get().write(key = DARK_THEME_PREFERENCE, !isDarkTheme)

            setDefaultNightMode(MODE_NIGHT_YES.takeIf { !isDarkTheme } ?: MODE_NIGHT_NO)
        }
    }

    private companion object {
        const val LOAD_CONVERSION_LIMIT: Int = 12

        const val SOURCE_CURRENCY_PREFERENCE: String = "source_currency"

        const val DARK_THEME_PREFERENCE: String = "dark_theme"
    }
}
