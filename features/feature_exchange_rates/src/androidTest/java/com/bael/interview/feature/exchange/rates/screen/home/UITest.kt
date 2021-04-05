package com.bael.interview.feature.exchange.rates.screen.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.bael.interview.domain.common.response.Response.Error
import com.bael.interview.domain.common.response.Response.Success
import com.bael.interview.feature.exchange.rates.R
import com.bael.interview.lib.database.CurrencyConverterDatabase
import com.bael.interview.lib.presentation.test.fragment.BaseFragmentTest
import com.bael.interview.lib.remote.response.ExchangeRatesResponse
import com.bael.interview.lib.remote.test.service.RemoteService
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matchers.containsString
import org.junit.Test
import java.io.IOException
import javax.inject.Inject

/**
 * Created by ErickSumargo on 04/04/21.
 */

@HiltAndroidTest
internal class UITest : BaseFragmentTest() {
    @Inject
    lateinit var service: RemoteService<ExchangeRatesResponse>

    @Inject
    lateinit var database: CurrencyConverterDatabase

    override fun setupTest() {}

    @Test
    fun givenCurrencyResponseError_thenErrorStateShouldShow() {
        runTest {
            // given
            service.submitResponse(
                response = Error(
                    error = IOException("Server error")
                )
            )

            // when
            launch<UI>()

            // then
            onView(
                allOf(
                    withId(R.id.descriptionText),
                    withText("Server error")
                )
            ).check(matches(isDisplayed()))

            onView(
                allOf(
                    withId(R.id.actionButton),
                    withText("Try again")
                )
            ).check(matches(isDisplayed()))
        }
    }

    @Test
    fun givenCurrencyResponseSuccess_thenFormWithSelectedCurrencyShouldShow() {
        runTest {
            // given
            service.submitResponse(
                response = Success(
                    data = ExchangeRatesResponse(
                        privacy = "",
                        quotes = mapOf(
                            "USDAED" to 0.0,
                            "USDAFN" to 0.0,
                            "USDALL" to 0.0,
                        ),
                        source = "USD",
                        success = true,
                        terms = "",
                        timestamp = 0L,
                        error = null
                    )
                )
            )

            // when
            launch<UI>()

            // then
            onView(
                allOf(
                    withId(R.id.amountInput),
                    withHint("Amount")
                )
            ).check(matches(isDisplayed()))

            onView(
                allOf(
                    withId(R.id.currencySpinner),
                    withSpinnerText(containsString("AED"))
                )
            ).check(matches(isDisplayed()))
        }
    }

    // And the rest... :)

    override fun clearTest() {
        database.closeConnection()
    }
}
