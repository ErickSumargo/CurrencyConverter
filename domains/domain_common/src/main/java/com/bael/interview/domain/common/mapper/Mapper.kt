package com.bael.interview.domain.common.mapper

/**
 * Created by ErickSumargo on 01/04/21.
 */

interface Mapper<I, O> {

    fun map(data: I): O
}
