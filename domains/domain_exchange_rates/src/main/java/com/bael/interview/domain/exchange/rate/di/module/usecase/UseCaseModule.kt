package com.bael.interview.domain.exchange.rate.di.module.usecase

import com.bael.interview.domain.exchange.rate.usecase.ConvertCurrencyUseCase
import com.bael.interview.domain.exchange.rate.usecase.DefaultConvertCurrencyUseCase
import com.bael.interview.domain.exchange.rate.usecase.DefaultLoadCurrencySymbolUseCase
import com.bael.interview.domain.exchange.rate.usecase.LoadCurrencySymbolUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/04/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal interface UseCaseModule {

    @Binds
    @Singleton
    fun bindLoadCurrencySymbolUseCase(
        useCase: DefaultLoadCurrencySymbolUseCase
    ): LoadCurrencySymbolUseCase

    @Binds
    @Singleton
    fun bindConvertCurrencyUseCase(
        useCase: DefaultConvertCurrencyUseCase
    ): ConvertCurrencyUseCase
}
