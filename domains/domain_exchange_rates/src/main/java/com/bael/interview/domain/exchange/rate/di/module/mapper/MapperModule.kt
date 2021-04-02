package com.bael.interview.domain.exchange.rate.di.module.mapper

import com.bael.interview.domain.common.mapper.Mapper
import com.bael.interview.domain.exchange.rate.mapper.CurrencyRateMapper
import com.bael.interview.domain.exchange.rate.mapper.CurrencySymbolMapper
import com.bael.interview.domain.exchange.rate.model.CurrencySymbol
import com.bael.interview.lib.database.entity.ExchangeRate
import com.bael.interview.lib.database.model.CurrencyRate
import com.bael.interview.lib.remote.response.ExchangeRatesResponse
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
internal interface MapperModule {

    @Binds
    @Singleton
    fun bindCurrencySymbolMapper(
        mapper: CurrencySymbolMapper
    ): Mapper<ExchangeRate, CurrencySymbol>

    @Binds
    @Singleton
    fun bindCurrencyRateMapper(
        mapper: CurrencyRateMapper
    ): Mapper<ExchangeRatesResponse, CurrencyRate>
}
