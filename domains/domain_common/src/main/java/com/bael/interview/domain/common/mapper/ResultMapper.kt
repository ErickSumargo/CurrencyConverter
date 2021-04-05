package com.bael.interview.domain.common.mapper

import javax.inject.Inject

/**
 * Created by ErickSumargo on 04/04/21.
 */

class ResultMapper<I, O> @Inject constructor(
    private val mapper: Mapper<I, O>
) : Mapper<Result<I>, Result<O>> {

    override fun map(data: Result<I>): Result<O> {
        return data.map(mapper::map)
    }
}
