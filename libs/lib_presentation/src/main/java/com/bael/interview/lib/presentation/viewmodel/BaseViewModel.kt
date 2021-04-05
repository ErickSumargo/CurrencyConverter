package com.bael.interview.lib.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PROTECTED
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bael.interview.domain.common.store.Store
import com.bael.interview.lib.presentation.event.EventStore
import com.bael.interview.lib.presentation.state.StateStore
import com.bael.interview.lib.threading.Thread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

abstract class BaseViewModel<S, E>(
    initState: S,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    @Inject
    protected lateinit var thread: Thread

    private val key: String
        get() = javaClass.name

    private val stateStore: Store<S> = StateStore(
        initState = restoreState() ?: initState
    )

    private val eventStore: Store<E> = EventStore()

    internal val stateFlow: Flow<S>
        get() = stateStore.stateFlow.onEach(::saveState)

    internal val eventFlow: Flow<E>
        get() = eventStore.stateFlow.drop(count = 1) // null as initial value

    protected val state: S
        get() = stateStore.stateFlow.value

    private fun restoreState(): S? {
        return savedStateHandle.get(key)
    }

    private fun saveState(state: S) {
        savedStateHandle.set(key, state)
    }

    @VisibleForTesting(otherwise = PROTECTED)
    fun render(newState: S) {
        stateStore.process(newState)
    }

    protected fun action(newEvent: E) {
        eventStore.process(newEvent)
    }
}
