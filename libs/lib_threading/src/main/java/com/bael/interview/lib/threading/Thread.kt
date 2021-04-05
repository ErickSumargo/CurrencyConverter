package com.bael.interview.lib.threading

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by ErickSumargo on 01/04/21.
 */

interface Thread {
    val main: CoroutineDispatcher

    val default: CoroutineDispatcher

    val io: CoroutineDispatcher

    fun reset()
}
