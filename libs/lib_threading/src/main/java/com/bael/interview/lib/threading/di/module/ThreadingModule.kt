package com.bael.interview.lib.threading.di.module

import com.bael.interview.lib.threading.DefaultThread
import com.bael.interview.lib.threading.Thread
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
interface ThreadingModule {

    @Binds
    @Singleton
    fun bindThread(thread: DefaultThread): Thread
}
