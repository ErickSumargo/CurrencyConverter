package com.bael.interview.feature.exchange.rates.screen.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.bael.interview.lib.presentation.test.fragment.BaseFragmentTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

/**
 * Created by ErickSumargo on 01/04/21.
 */

@HiltAndroidTest
internal class UITest : BaseFragmentTest() {

    override fun setupTest() {}

    @Test
    fun test() {
        launch<UI> {
            onView(
                withText("hello")
            ).check(matches(isDisplayed()))
        }
    }
}
