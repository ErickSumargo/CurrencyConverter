package com.bael.interview.domain.exchange.rate.di.module.usecase

import com.bael.interview.domain.exchange.rate.usecase.ConvertCurrencyUseCase
import com.bael.interview.domain.exchange.rate.usecase.DefaultConvertCurrencyUseCase
import com.bael.interview.domain.exchange.rate.usecase.DefaultLoadCurrencyConversionUseCase
import com.bael.interview.domain.exchange.rate.usecase.DefaultLoadCurrencyUseCase
import com.bael.interview.domain.exchange.rate.usecase.LoadCurrencyConversionUseCase
import com.bael.interview.domain.exchange.rate.usecase.LoadCurrencyUseCase
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
    fun bindLoadCurrencyUseCase(
        useCase: DefaultLoadCurrencyUseCase
    ): LoadCurrencyUseCase

    @Binds
    @Singleton
    fun bindLoadCurrencyConversionUseCase(
        useCase: DefaultLoadCurrencyConversionUseCase
    ): LoadCurrencyConversionUseCase

    @Binds
    @Singleton
    fun bindConvertCurrencyUseCase(
        useCase: DefaultConvertCurrencyUseCase
    ): ConvertCurrencyUseCase
}
