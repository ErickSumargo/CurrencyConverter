package com.bael.interview.lib.remote.di.module.interceptor

import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils.shouldEnableFlipper
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.leakcanary.LeakCanaryFlipperPlugin
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/04/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal object FlipperModule {

    @Provides
    @Singleton
    fun provideDatabasesFlipperPlugin(
        @ApplicationContext context: Context
    ): DatabasesFlipperPlugin {
        return DatabasesFlipperPlugin(context)
    }

    @Provides
    @Singleton
    fun provideInspectorFlipperPlugin(
        @ApplicationContext context: Context
    ): InspectorFlipperPlugin {
        return InspectorFlipperPlugin(context, DescriptorMapping.withDefaults())
    }

    @Provides
    @Singleton
    fun provideLeakCanaryFlipperPlugin(): LeakCanaryFlipperPlugin {
        return LeakCanaryFlipperPlugin()
    }

    @Provides
    @Singleton
    fun provideNetworkFlipperPlugin(): NetworkFlipperPlugin {
        return NetworkFlipperPlugin()
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesFlipperPlugin(
        @ApplicationContext context: Context
    ): SharedPreferencesFlipperPlugin {
        return SharedPreferencesFlipperPlugin(context)
    }

    @Provides
    @Singleton
    fun provideFlipperOkHttpInterceptor(
        @ApplicationContext context: Context,
        inspectorFlipperPlugin: InspectorFlipperPlugin,
        networkFlipperPlugin: NetworkFlipperPlugin,
        leakCanaryFlipperPlugin: LeakCanaryFlipperPlugin,
        databasesFlipperPlugin: DatabasesFlipperPlugin,
        sharedPreferencesFlipperPlugin: SharedPreferencesFlipperPlugin
    ): FlipperOkhttpInterceptor {
        SoLoader.init(context, false)
        if (shouldEnableFlipper(context)) {
            AndroidFlipperClient.getInstance(context).apply {
                addPlugin(inspectorFlipperPlugin)
                addPlugin(networkFlipperPlugin)
                addPlugin(leakCanaryFlipperPlugin)
                addPlugin(databasesFlipperPlugin)
                addPlugin(sharedPreferencesFlipperPlugin)
            }.start()
        }

        return FlipperOkhttpInterceptor(networkFlipperPlugin)
    }
}
