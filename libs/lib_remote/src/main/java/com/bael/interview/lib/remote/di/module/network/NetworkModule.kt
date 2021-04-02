package com.bael.interview.lib.remote.di.module.network

import com.bael.interview.lib.remote.network.DefaultNetwork
import com.bael.interview.lib.remote.network.Network
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
internal interface NetworkModule {

    @Binds
    @Singleton
    fun bindNetwork(network: DefaultNetwork): Network
}
