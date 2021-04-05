package com.bael.interview.lib.remote.test.service

import com.bael.interview.domain.common.response.Response
import com.bael.interview.domain.common.response.Response.Error
import com.bael.interview.domain.common.response.Response.Success
import java.io.IOException
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

/**
 * Created by ErickSumargo on 04/04/21.
 */

internal abstract class FakeBaseService<T> : RemoteService<T> {
    private var response: Response<T>? = null

    protected val result: Result<T>
        get() {
            return when (response) {
                is Error -> failure(exception = (response as Error).error as Throwable)
                is Success -> success((response as Success).data)
                else -> failure(exception = IOException("Connection error"))
            }
        }

    override fun submitResponse(response: Response<T>) {
        this.response = response
    }
}
