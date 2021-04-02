package com.bael.interview.feature.exchange.rates.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bael.interview.feature.exchange.rates.databinding.ScreenHomeBinding
import com.bael.interview.feature.exchange.rates.databinding.ScreenHomeBinding.inflate
import com.bael.interview.lib.presentation.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by ErickSumargo on 01/04/21.
 */

@AndroidEntryPoint
internal class UI : BaseFragment<ScreenHomeBinding, ViewModel, State, Event>() {
    override val viewModel: ViewModel by viewModels()

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ScreenHomeBinding {
        return inflate(inflater, container, false)
    }

    override suspend fun onViewLoaded(savedInstanceState: Bundle?) {
        viewBinding.button.setOnClickListener {
        }
    }

    override suspend fun render(state: State) {}

    override suspend fun action(event: Event) {}
}
