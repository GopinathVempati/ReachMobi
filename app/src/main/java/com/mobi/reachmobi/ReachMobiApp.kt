package com.mobi.reachmobi

import android.app.Application
import android.content.Context

class ReachMobiApp : Application() {
    companion object { var appContext: Context? = null }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}