package com.bael.interview.lib.remote.interceptor

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class CacheControlInterceptor @Inject constructor(
    private val cacheControl: CacheControl
) : Interceptor {

    override fun intercept(chain: Chain): Response {
        val request = chain.request()
            .newBuilder()
            .cacheControl(cacheControl)
            .build()
        return chain.proceed(request)
    }
}
