package com.bael.interview.lib.preference.di.module

import com.bael.interview.lib.preference.DataStorePreference
import com.bael.interview.lib.preference.Preference
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
interface PreferenceModule {

    @Binds
    @Singleton
    fun bindPreference(preference: DataStorePreference): Preference
}
