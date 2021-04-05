package com.bael.interview.lib.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PROTECTED
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle.State.RESUMED
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.bael.interview.lib.presentation.viewmodel.BaseViewModel
import com.bael.interview.lib.threading.Thread
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by ErickSumargo on 04/04/21.
 */

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel<S, E>, S, E> : Fragment() {
    @Inject
    lateinit var thread: Thread
        @VisibleForTesting(otherwise = PROTECTED)
        get

    private var _viewBinding: VB? = null

    protected val viewBinding: VB
        get() = _viewBinding!!

    abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        observeState()
        observeEvent()

        _viewBinding = createView(inflater, container)
        return _viewBinding?.root
    }

    abstract fun createView(inflater: LayoutInflater, container: ViewGroup?): VB

    private fun observeState() {
        viewModel.stateFlow
            .flowWithLifecycle(
                lifecycle = viewLifecycleOwner.lifecycle,
                minActiveState = RESUMED
            )
            .onEach(::render)
            .launchIn(scope = viewLifecycleOwner.lifecycleScope)
    }

    private fun observeEvent() {
        viewModel.eventFlow
            .flowWithLifecycle(
                lifecycle = viewLifecycleOwner.lifecycle,
                minActiveState = RESUMED
            )
            .onEach(::action)
            .launchIn(scope = viewLifecycleOwner.lifecycleScope)
    }

    abstract suspend fun render(state: S)

    abstract suspend fun action(event: E)

    protected fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    protected fun back() {
        findNavController().navigateUp()
    }

    protected fun showMessage(message: String?) {
        message ?: return
        Snackbar.make(viewBinding.root, message, LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}
