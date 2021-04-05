package com.bael.interview.lib.remote.di.module.rest

import com.bael.interview.lib.remote.rest.CurrencyLayerREST
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 04/04/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal object RESTModule {

    @Provides
    @Singleton
    fun provideCurrencyLayerREST(retrofit: Retrofit): CurrencyLayerREST {
        return retrofit.create(CurrencyLayerREST::class.java)
    }
}
