package com.bael.interview.lib.remote.service

import retrofit2.Response
import java.io.IOException

/**
 * Created by ErickSumargo on 01/04/21.
 */

abstract class BaseService {

    protected suspend inline fun <T> request(
        crossinline request: suspend () -> Response<T>
    ): Result<T> {
        return runCatching {
            val response = request()
            if (!response.isSuccessful) {
                val message = response.message()
                throw IOException(message)
            }
            response.body()!!
        }
    }
}
