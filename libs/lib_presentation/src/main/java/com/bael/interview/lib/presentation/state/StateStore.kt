package com.bael.interview.lib.presentation.state

import com.bael.interview.domain.common.store.Store
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class StateStore<S>(initState: S) : Store<S> {
    override val stateFlow: MutableStateFlow<S> = MutableStateFlow(value = initState)

    override fun process(item: S) {
        stateFlow.value = item
    }
}
