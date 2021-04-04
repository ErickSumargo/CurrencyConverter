package com.bael.interview.feature.exchange.rates.screen.home

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal sealed class Event {

    class DisplayCurrencyError(val message: String) : Event()

    class ReloadCurrency : Event()
}
