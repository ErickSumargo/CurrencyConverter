package com.bael.interview.lib.remote.test.service

import com.bael.interview.domain.common.response.Response

/**
 * Created by ErickSumargo on 01/04/21.
 */

interface RemoteService<T> {

    fun submitResponse(response: Response<T>)
}
