package com.bael.interview.feature.exchange.rates.di.module.screen

import com.bael.interview.domain.common.response.Response.Empty
import com.bael.interview.domain.exchange.rate.model.Currency
import com.bael.interview.feature.exchange.rates.screen.home.State
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by ErickSumargo on 01/04/21.
 */

@Module
@InstallIn(value = [ViewModelComponent::class, FragmentComponent::class])
internal object HomeScreenModule {

    @Provides
    fun provideState(): State {
        return State(
            amount = 0.0,
            source = Currency(id = ""),
            currencyResponse = Empty,
            currencyConversionResponses = listOf()
        )
    }
}
