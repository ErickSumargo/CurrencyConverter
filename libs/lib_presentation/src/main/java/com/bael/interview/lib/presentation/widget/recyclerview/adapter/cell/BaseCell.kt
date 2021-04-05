package com.bael.interview.lib.presentation.widget.recyclerview.adapter.cell

import android.content.Context
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding

/**
 * Created by ErickSumargo on 04/04/21.
 */

abstract class BaseCell<VB : ViewBinding, I : Any>(
    protected val viewBinding: VB
) : ViewHolder(viewBinding.root) {
    val context: Context
        get() = viewBinding.root.context

    internal lateinit var itemCache: I
        private set

    internal fun cacheItem(item: I) {
        itemCache = item
    }

    abstract fun render(state: I)
}
