@file:Suppress("UNCHECKED_CAST")

package com.bael.interview.lib.presentation.event

import com.bael.interview.domain.common.store.Store
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Created by ErickSumargo on 04/04/21.
 */

internal class EventStore<E> : Store<E> {
    override val stateFlow: MutableStateFlow<E> = MutableStateFlow(value = null as E)

    override fun process(item: E) {
        stateFlow.value = item
    }
}
