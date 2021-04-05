package com.bael.interview.lib.database.di.module.repository

import com.bael.interview.lib.database.repository.DefaultExchangeRateRepository
import com.bael.interview.lib.database.repository.ExchangeRateRepository
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
internal interface RepositoryModule {

    @Binds
    @Singleton
    fun bindExchangeRepository(
        repository: DefaultExchangeRateRepository
    ): ExchangeRateRepository
}
