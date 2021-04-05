package com.bael.interview.lib.database.di.module

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.bael.interview.lib.database.CurrencyConverterDatabase
import com.bael.interview.lib.database.CurrencyConverterRoomDatabase
import com.bael.interview.lib.database.di.qualifier.DatabaseNameQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 04/04/21.
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    @DatabaseNameQualifier
    fun provideDatabaseName(): String {
        return "currency_converter.db"
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        @DatabaseNameQualifier databaseName: String
    ): CurrencyConverterDatabase {
        return databaseBuilder(
            context,
            CurrencyConverterRoomDatabase::class.java,
            databaseName
        ).build()
    }
}
