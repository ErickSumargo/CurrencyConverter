package com.bael.interview.lib.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by ErickSumargo on 04/04/21.
 */

internal class ContentTypeInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header("Accept", "application/json")
            .build()
        return chain.proceed(request)
    }
}
