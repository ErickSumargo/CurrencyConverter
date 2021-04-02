package com.bael.interview

import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import android.os.StrictMode.setThreadPolicy
import android.os.StrictMode.setVmPolicy
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by ErickSumargo on 01/04/21.
 */

@HiltAndroidApp
internal class CurrencyConverterDebugApplication : CurrencyConverterApplication() {

    override fun onCreate() {
        super.onCreate()
        setStrictMode()
    }

    private fun setStrictMode() {
        setThreadPolicy(
            ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )

        setVmPolicy(
            VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )
    }
}
