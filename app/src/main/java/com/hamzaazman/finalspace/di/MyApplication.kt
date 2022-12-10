package com.hamzaazman.finalspace.di

import android.app.Application
import com.hamzaazman.finalspace.util.hasInternetConnection
import dagger.hilt.android.HiltAndroidApp
import kotlin.properties.Delegates

@HiltAndroidApp
class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        hasInternetConnection(this@MyApplication).let { result ->
            networkResult = result
        }
    }

    companion object {
        var networkResult = false
    }
}