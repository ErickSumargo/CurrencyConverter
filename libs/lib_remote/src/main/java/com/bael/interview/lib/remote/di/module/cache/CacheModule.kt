package com.bael.interview.lib.remote.di.module.cache

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.CacheControl
import java.util.concurrent.TimeUnit.MINUTES
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 04/04/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal object CacheModule {

    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache {
        return Cache(context.cacheDir, 5 * 1024 * 1024)
    }

    @Singleton
    @Provides
    fun provideCacheControl(): CacheControl {
        return CacheControl.Builder().apply {
            maxAge(30, MINUTES)
        }.build()
    }
}
