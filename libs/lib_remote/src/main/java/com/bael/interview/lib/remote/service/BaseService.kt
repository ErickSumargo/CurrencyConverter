package com.bael.interview.lib.remote.service

import com.bael.interview.lib.remote.network.Network
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

/**
 * Created by ErickSumargo on 04/04/21.
 */

abstract class BaseService {
    @Inject
    lateinit var network: Network

    protected suspend inline fun <T> request(
        crossinline request: suspend () -> Response<T>
    ): Result<T> {
        return try {
            if (!network.isConnected) {
                failure(exception = IOException("No internet connection"))
            } else {
                val response = request()
                if (!response.isSuccessful) {
                    val message = response.message()
                    failure(exception = IOException(message))
                } else {
                    success(response.body()!!)
                }
            }
        } catch (cause: Exception) {
            failure(exception = IOException("Service is temporarily unavailable"))
        }
    }
}
