package com.example.demomvvmapp.app

import android.app.Application
import android.util.Log
import com.example.demomvvmapp.koin.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MoonPhaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("Moon","MoonPhaseApplication create")
        Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(applicationContext)
            modules(allModules)
        }
    }
}
