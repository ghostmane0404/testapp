package com.example.testproject

import android.app.Application
import android.content.Context
import com.example.testproject.core.data.di.viewModelModule
import com.example.testproject.core.data.storage.PreferencesUtil
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class TestProjectApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TestProjectApp)
            koin.loadModules(getModule())
        }
        PreferencesUtil.init(this)
    }

    private fun getModule(): List<Module> {
        return listOf(viewModelModule)
    }

    override fun attachBaseContext(base: Context) {
        // MultiDex.install(this)
        super.attachBaseContext(base)
    }
}