package com.bael.interview.lib.presentation.widget.listener

import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener

/**
 * Created by ErickSumargo on 04/04/21.
 */

class OnSpinnerItemSelectedListener(
    private val onItemSelected: (Int) -> Unit
) : OnItemSelectedListener {

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        onItemSelected(pos)
    }
}
