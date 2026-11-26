package com.tambo360.app.app

import android.app.Application
import com.tambo360.app.core.network.NetworkMonitor
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        NetworkMonitor.init(this)
    }
}
