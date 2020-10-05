package com.ilkeruzer.minibakkal

import android.app.Application
import com.ilkeruzer.minibakkal.di.appModule
import com.ilkeruzer.minibakkal.di.localeModule
import com.ilkeruzer.minibakkal.di.networkModule
import com.ilkeruzer.minibakkal.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(
                appModule,
                networkModule,
                localeModule,
                viewModelModule
            ))
        }
    }
}