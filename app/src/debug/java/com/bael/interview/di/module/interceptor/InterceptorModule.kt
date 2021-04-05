package com.bael.interview.di.module.interceptor

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/04/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal object InterceptorModule {

    @Provides
    @Singleton
    fun provideFlipperOkhttpInterceptor(
        networkFlipperPlugin: NetworkFlipperPlugin
    ): Interceptor {
        return FlipperOkhttpInterceptor(networkFlipperPlugin)
    }
}
