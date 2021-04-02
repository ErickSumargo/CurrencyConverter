package com.bael.interview.lib.remote.interceptor

import com.bael.interview.domain.common.exception.NoNetworkException
import com.bael.interview.lib.remote.network.Network
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/04/21.
 */

@Singleton
internal class NetworkInterceptor @Inject constructor(
    network: Network
) : Interceptor,
    Network by network {

    override fun intercept(chain: Chain): Response {
        if (isConnected.not()) throw NoNetworkException()

        val request = chain.request()
        return chain.proceed(request)
    }
}
