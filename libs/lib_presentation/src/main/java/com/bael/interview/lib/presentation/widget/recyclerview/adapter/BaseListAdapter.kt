package com.bael.interview.lib.presentation.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import com.bael.interview.lib.presentation.widget.recyclerview.adapter.cell.BaseCell
import kotlin.math.ceil

/**
 * Created by ErickSumargo on 01/04/21.
 */

abstract class BaseListAdapter<I : Any, C : BaseCell<*, I>>(
    diffCallback: ItemCallback<I>,
    private val itemPerPage: Int
) : ListAdapter<I, C>(diffCallback) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): C {
        val inflater = from(viewGroup.context)
        return createCell(inflater, viewGroup)
    }

    override fun onBindViewHolder(cell: C, position: Int) {
        val item = getItem(position)

        cell.cacheItem(item)
        cell.render(item)

        if (position < itemCount - 1) return
        onNextPage(page = ceil(itemCount.toDouble() / itemPerPage.toDouble()).toInt() + 1)
    }

    abstract fun createCell(inflater: LayoutInflater, viewGroup: ViewGroup): C

    abstract fun onNextPage(page: Int)

    fun getItemAt(position: Int): I? {
        return if (position < 0 || position >= itemCount) null
        else getItem(position)
    }

    fun lastItem(): I? {
        return getItemAt(position = itemCount - 1)
    }

    fun clearData() {
        submitList(listOf())
    }
}
