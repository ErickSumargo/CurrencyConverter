package com.bael.interview.lib.database.test.di.module

import com.bael.interview.lib.database.CurrencyConverterDatabase
import com.bael.interview.lib.database.di.module.DatabaseModule
import com.bael.interview.lib.database.test.FakeCurrencyConverterDatabase
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
    replaces = [DatabaseModule::class]
)
internal interface DatabaseTestModule {

    @Binds
    @Singleton
    fun bindDatabase(database: FakeCurrencyConverterDatabase): CurrencyConverterDatabase
}
