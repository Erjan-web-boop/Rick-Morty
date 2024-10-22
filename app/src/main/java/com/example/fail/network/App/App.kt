package com.example.fail.network.App

import android.app.Application
import com.example.fail.network.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            androidLogger()
            modules(appModule)
        }
    }
}