package com.bael.interview.lib.presentation.test.fragment

import androidx.fragment.app.Fragment
import com.bael.interview.lib.presentation.fragment.BaseFragment
import com.bael.interview.lib.threading.Thread
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule

/**
 * Created by ErickSumargo on 01/04/21.
 */

abstract class BaseFragmentTest {
    @get:Rule
    internal val hiltRule = HiltAndroidRule(this)

    protected lateinit var testDispatcher: TestCoroutineDispatcher

    val thread: Thread = object : Thread {
        override val main: CoroutineDispatcher
            get() = testDispatcher

        override val default: CoroutineDispatcher
            get() = testDispatcher

        override val io: CoroutineDispatcher
            get() = testDispatcher
    }

    @Before
    internal fun setup() {
        hiltRule.inject()

        testDispatcher = TestCoroutineDispatcher()
        Dispatchers.setMain(testDispatcher)

        setupTest()
    }

    abstract fun setupTest()

    protected inline fun <reified F : BaseFragment<*, *, *, *>> launch(
        crossinline test: (Fragment) -> Unit
    ) {
        launchFragmentInHiltContainer<F> {
            (this as F).thread = this@BaseFragmentTest.thread
            test(this)
        }
    }
}
