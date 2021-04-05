package com.bael.interview.di.module.client

import android.content.Context
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.leakcanary.LeakCanaryFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
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
}
