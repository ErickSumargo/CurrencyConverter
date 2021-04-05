package com.bael.interview.lib.remote.di.module.service

import com.bael.interview.lib.remote.service.CurrencyLayerService
import com.bael.interview.lib.remote.service.DefaultCurrencyLayerService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 04/04/21.
 */

@Module
@InstallIn(SingletonComponent::class)
interface ServiceModule {

    @Binds
    @Singleton
    fun bindCurrencyLayerService(service: DefaultCurrencyLayerService): CurrencyLayerService
}
