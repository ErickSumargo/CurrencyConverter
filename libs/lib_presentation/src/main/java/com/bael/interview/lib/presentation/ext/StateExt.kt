package com.bael.interview.lib.presentation.ext

import com.bael.interview.lib.presentation.state.BaseState

/**
 * Created by ErickSumargo on 01/04/21.
 */

fun <S : BaseState> S.reduce(reduceState: S.() -> S): S {
    return reduceState()
}
