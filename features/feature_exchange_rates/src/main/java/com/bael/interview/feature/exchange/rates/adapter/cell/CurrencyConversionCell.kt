package com.bael.interview.feature.exchange.rates.adapter.cell

import com.bael.interview.domain.exchange.rate.model.CurrencyConversion
import com.bael.interview.feature.exchange.rates.databinding.CellCurrencyConversionBinding
import com.bael.interview.lib.presentation.widget.recyclerview.adapter.cell.BaseCell

/**
 * Created by ErickSumargo on 04/04/21.
 */

internal class CurrencyConversionCell(
    viewBinding: CellCurrencyConversionBinding
) : BaseCell<CellCurrencyConversionBinding, CurrencyConversion>(viewBinding) {

    override fun render(state: CurrencyConversion) {
        viewBinding.currencyText.also { view ->
            view.text = state.target.id
        }

        viewBinding.rateText.also { view ->
            view.text = "${state.rate}"
        }
    }
}
