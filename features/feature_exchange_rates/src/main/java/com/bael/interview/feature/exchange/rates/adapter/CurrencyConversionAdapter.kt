package com.bael.interview.feature.exchange.rates.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bael.interview.domain.exchange.rate.model.CurrencyConversion
import com.bael.interview.feature.exchange.rates.adapter.cell.CurrencyConversionCell
import com.bael.interview.feature.exchange.rates.adapter.diffcallback.CurrencyConversionDiffCallback
import com.bael.interview.feature.exchange.rates.databinding.CellCurrencyConversionBinding.inflate
import com.bael.interview.lib.presentation.widget.recyclerview.adapter.BaseListAdapter

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class CurrencyConversionAdapter(
    diffCallback: CurrencyConversionDiffCallback,
    itemPerPage: Int,
    private val onNextPageListener: (page: Int) -> Unit
) : BaseListAdapter<CurrencyConversion, CurrencyConversionCell>(diffCallback, itemPerPage) {

    override fun createCell(
        inflater: LayoutInflater,
        viewGroup: ViewGroup
    ): CurrencyConversionCell {
        val viewBinding = inflate(inflater, viewGroup, false)
        return CurrencyConversionCell(viewBinding)
    }

    override fun onNextPage(page: Int) {
        onNextPageListener(page)
    }
}
