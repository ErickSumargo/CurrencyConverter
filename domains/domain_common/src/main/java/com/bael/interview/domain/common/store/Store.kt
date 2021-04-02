package com.bael.interview.domain.common.store

import kotlinx.coroutines.flow.StateFlow

/**
 * Created by ErickSumargo on 01/04/21.
 */

interface Store<S> {
    val stateFlow: StateFlow<S>

    fun process(item: S)
}
