package com.bael.interview.lib.threading

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import javax.inject.Inject

/**
 * Created by ErickSumargo on 04/04/21.
 */

class DefaultThread @Inject constructor() : Thread {
    override val main: CoroutineDispatcher
        get() = Main

    override val default: CoroutineDispatcher
        get() = Default

    override val io: CoroutineDispatcher
        get() = IO

    override fun reset() {}
}
