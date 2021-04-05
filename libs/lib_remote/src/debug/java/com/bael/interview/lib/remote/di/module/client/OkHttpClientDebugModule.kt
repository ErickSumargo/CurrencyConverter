package com.bael.interview.lib.remote.di.module.client

import com.bael.interview.lib.remote.interceptor.CacheControlInterceptor
import com.bael.interview.lib.remote.interceptor.ContentTypeInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 04/04/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal object OkHttpClientDebugModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        contentTypeInterceptor: ContentTypeInterceptor,
        cacheControlInterceptor: CacheControlInterceptor,
        flipperOkhttpInterceptor: Interceptor,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(contentTypeInterceptor)
            .addNetworkInterceptor(cacheControlInterceptor)
            .addNetworkInterceptor(flipperOkhttpInterceptor)
            .cache(cache)
            .build()
    }
}
