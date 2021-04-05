@file:Suppress("UNCHECKED_CAST")

package com.bael.interview.lib.remote.test.di.module.service

import com.bael.interview.lib.remote.di.module.service.ServiceModule
import com.bael.interview.lib.remote.response.ExchangeRatesResponse
import com.bael.interview.lib.remote.service.CurrencyLayerService
import com.bael.interview.lib.remote.test.service.FakeCurrencyLayerService
import com.bael.interview.lib.remote.test.service.RemoteService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 04/04/21.
 */

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ServiceModule::class]
)
internal interface ServiceTestModule {

    @Binds
    @Singleton
    fun bindCurrencyLayerService(service: FakeCurrencyLayerService): CurrencyLayerService

    companion object {

        @Provides
        fun provideCurrencyLayerService(
            service: CurrencyLayerService
        ): RemoteService<ExchangeRatesResponse> {
            return service as RemoteService<ExchangeRatesResponse>
        }
    }
}
