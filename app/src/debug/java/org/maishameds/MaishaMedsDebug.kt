package org.maishameds

import android.os.Build
import android.os.StrictMode
import com.facebook.stetho.Stetho

class MaishaMedsDebug : MaishaMeds() {

    override fun onCreate() {
        super.onCreate()

        initStetho()
        initStrictMode()
    }

    private fun initStetho() {
        when {
            !isRoboUnitTest() -> {
                Stetho.initializeWithDefaults(this)
            }
        }
    }

    private fun isRoboUnitTest(): Boolean = "robolectric" == Build.FINGERPRINT

    private fun initStrictMode() {
        val threadPolicy = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                StrictMode.ThreadPolicy.Builder()
                    .detectDiskWrites()
                    .detectNetwork()
                    .detectCustomSlowCalls()
                    .penaltyLog()
                    .penaltyDeath()
                    .detectResourceMismatches()
            }
            else -> {
                StrictMode.ThreadPolicy.Builder()
                    .detectDiskWrites()
                    .detectNetwork()
                    .detectCustomSlowCalls()
                    .penaltyLog()
                    .penaltyDeath()
            }
        }
        StrictMode.setThreadPolicy(threadPolicy.build())

        val vmPolicy = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                StrictMode.VmPolicy.Builder()
                    .detectLeakedClosableObjects()
                    .detectLeakedSqlLiteObjects()
                    .detectFileUriExposure()
                    .penaltyLog()
                    .penaltyDeath()
                    .detectCleartextNetwork()
                    .detectActivityLeaks()
            }
            else -> {
                StrictMode.VmPolicy.Builder()
                    .detectLeakedClosableObjects()
                    .detectLeakedSqlLiteObjects()
                    .detectFileUriExposure()
                    .penaltyLog()
                    .penaltyDeath()
                    .detectActivityLeaks()
            }
        }
        StrictMode.setVmPolicy(vmPolicy.build())
    }
}