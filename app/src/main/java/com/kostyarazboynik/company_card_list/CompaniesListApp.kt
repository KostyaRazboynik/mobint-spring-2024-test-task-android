package com.kostyarazboynik.company_card_list

import android.app.Application
import android.os.SystemClock
import com.kostyarazboynik.company_card_list.dagger.AppComponent
import com.kostyarazboynik.company_card_list.timer.StartTimeHolder

class CompaniesListApp : Application() {

    private var isInitialized = false

    override fun onCreate() {
        super.onCreate()
        Logger.setIsDebug(true)
        Logger.d(TAG, "Start time is ${StartTimeHolder.timer.elapsed()}ms")

        appStartTime = SystemClock.elapsedRealtime()

        initialize()
    }

    private fun initialize() {
        if (isInitialized) {
            Logger.d(TAG, "already initialized")
            return
        }
        Logger.d(TAG, "initializing")
        isInitialized = true
        AppComponent.init(this)
        AppComponent.component.inject(this)
    }

    companion object {
        private const val TAG = "CompaniesListApp"
        var appStartTime = 0L
            private set
    }
}
