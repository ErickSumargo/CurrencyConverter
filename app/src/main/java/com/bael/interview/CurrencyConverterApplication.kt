package com.bael.interview

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import com.bael.interview.lib.preference.Preference
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

abstract class CurrencyConverterApplication : Application() {
    @Inject
    lateinit var preference: Preference

    override fun onCreate() {
        super.onCreate()
        runBlocking {
            val isDarkTheme = preference.read(key = DARK_THEME_PREFERENCE, false)
            setDefaultNightMode(MODE_NIGHT_YES.takeIf { isDarkTheme } ?: MODE_NIGHT_NO)
        }
    }

    private companion object {
        const val DARK_THEME_PREFERENCE: String = "dark_theme"
    }
}
