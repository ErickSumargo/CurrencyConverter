package com.bael.interview.feature.exchange.rates.adapter.diffcallback

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.bael.interview.domain.exchange.rate.model.CurrencyConversion

/**
 * Created by ErickSumargo on 04/04/21.
 */

internal class CurrencyConversionDiffCallback : ItemCallback<CurrencyConversion>() {

    override fun areItemsTheSame(
        oldItem: CurrencyConversion,
        newItem: CurrencyConversion
    ): Boolean {
        return oldItem.target.id == newItem.target.id
    }

    override fun areContentsTheSame(
        oldItem: CurrencyConversion,
        newItem: CurrencyConversion
    ): Boolean {
        return oldItem == newItem
    }
}
