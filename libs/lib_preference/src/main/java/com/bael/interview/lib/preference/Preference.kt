package com.bael.interview.lib.preference

/**
 * Created by ErickSumargo on 04/04/21.
 */

interface Preference {

    suspend fun <T> read(key: String, defaultValue: T): T

    suspend fun <T> write(key: String, value: T)
}
