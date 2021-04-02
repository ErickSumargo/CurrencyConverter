package com.bael.interview.lib.presentation.widget.recyclerview.adapter

import com.bael.interview.lib.presentation.widget.recyclerview.adapter.cell.BaseCell

/**
 * Created by ErickSumargo on 01/04/21.
 */

abstract class SingleItemAdapter<I : Any, C : BaseCell<*, I>> : BaseAdapter<I, C>() {

    override fun getItemCount(): Int = 1
}
