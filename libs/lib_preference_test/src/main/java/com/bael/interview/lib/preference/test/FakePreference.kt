@file:Suppress("UNCHECKED_CAST")

package com.bael.interview.lib.preference.test

import com.bael.interview.lib.preference.Preference
import javax.inject.Inject

/**
 * Created by ErickSumargo on 04/04/21.
 */

internal class FakePreference @Inject constructor() : Preference {
    private val container: MutableMap<String, Any> = HashMap()

    override suspend fun <T> read(key: String, defaultValue: T): T {
        return container[key] as T ?: defaultValue
    }

    override suspend fun <T> write(key: String, value: T) {
        container[key] = value as Any
    }
}
