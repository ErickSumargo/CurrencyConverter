@file:Suppress("UNCHECKED_CAST")

package com.bael.interview.lib.remote.test.di.module.service

import com.bael.interview.lib.remote.di.module.service.ServiceModule
import com.bael.interview.lib.remote.service.CurrencyLayerService
import com.bael.interview.lib.remote.test.service.FakeCurrencyLayerService
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/04/21.
 */

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ServiceModule::class]
)
internal interface ServiceTestModule {

    @Binds
    @Singleton
    fun bindDadsService(service: FakeCurrencyLayerService): CurrencyLayerService
}
