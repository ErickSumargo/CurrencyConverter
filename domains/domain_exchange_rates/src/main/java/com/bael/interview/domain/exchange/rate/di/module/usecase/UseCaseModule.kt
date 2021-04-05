package com.bael.interview.domain.exchange.rate.di.module.usecase

import com.bael.interview.domain.exchange.rate.interactor.ConvertCurrencyInteractor
import com.bael.interview.domain.exchange.rate.interactor.LoadCurrencyConversionInteractor
import com.bael.interview.domain.exchange.rate.interactor.LoadCurrencyInteractor
import com.bael.interview.domain.exchange.rate.usecase.ConvertCurrencyUseCase
import com.bael.interview.domain.exchange.rate.usecase.LoadCurrencyConversionUseCase
import com.bael.interview.domain.exchange.rate.usecase.LoadCurrencyUseCase
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
internal interface UseCaseModule {

    @Binds
    @Singleton
    fun bindLoadCurrencyUseCase(
        interactor: LoadCurrencyInteractor
    ): LoadCurrencyUseCase

    @Binds
    @Singleton
    fun bindLoadCurrencyConversionUseCase(
        interactor: LoadCurrencyConversionInteractor
    ): LoadCurrencyConversionUseCase

    @Binds
    @Singleton
    fun bindConvertCurrencyUseCase(
        interactor: ConvertCurrencyInteractor
    ): ConvertCurrencyUseCase
}
