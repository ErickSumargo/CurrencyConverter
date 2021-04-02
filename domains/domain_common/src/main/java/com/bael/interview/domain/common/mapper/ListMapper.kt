package com.bael.interview.domain.common.mapper

import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

class ListMapper<I, O> @Inject constructor(
    private val mapper: Mapper<I, O>
) : Mapper<List<I>, List<O>> {

    override fun map(data: List<I>): List<O> {
        return data.map(mapper::map)
    }
}
