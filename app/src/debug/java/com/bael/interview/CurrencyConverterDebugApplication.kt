package com.bael.interview

import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import android.os.StrictMode.setThreadPolicy
import android.os.StrictMode.setVmPolicy
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils.shouldEnableFlipper
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.leakcanary.LeakCanaryFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * Created by ErickSumargo on 04/04/21.
 */

@HiltAndroidApp
internal class CurrencyConverterDebugApplication : CurrencyConverterApplication() {
    /**
     * Duplicate classes compile exception issued by instrumentation test.
     * Workaround is initializing Flipper in :app module.
     */
    @Inject
    lateinit var inspectorFlipperPlugin: InspectorFlipperPlugin

    @Inject
    lateinit var networkFlipperPlugin: NetworkFlipperPlugin

    @Inject
    lateinit var leakCanaryFlipperPlugin: LeakCanaryFlipperPlugin

    @Inject
    lateinit var databasesFlipperPlugin: DatabasesFlipperPlugin

    @Inject
    lateinit var sharedPreferencesFlipperPlugin: SharedPreferencesFlipperPlugin

    override fun onCreate() {
        super.onCreate()
        setFlipper()
        setStrictMode()
    }

    private fun setFlipper() {
        SoLoader.init(this, false)
        if (shouldEnableFlipper(this)) {
            AndroidFlipperClient.getInstance(this).apply {
                addPlugin(inspectorFlipperPlugin)
                addPlugin(networkFlipperPlugin)
                addPlugin(leakCanaryFlipperPlugin)
                addPlugin(databasesFlipperPlugin)
                addPlugin(sharedPreferencesFlipperPlugin)
            }.start()
        }
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
