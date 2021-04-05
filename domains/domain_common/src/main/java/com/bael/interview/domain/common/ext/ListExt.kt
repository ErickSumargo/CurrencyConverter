package com.bael.interview.domain.common.ext

import com.bael.interview.domain.common.response.Response
import com.bael.interview.domain.common.response.Response.Success

/**
 * Created by ErickSumargo on 04/04/21.
 */

fun <T> List<Response<T>>.findSuccess(): Success<T>? {
    return firstOrNull { it is Success } as? Success
}
