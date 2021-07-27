package com.poke.pagination

import android.app.Application
import com.poke.core.di.coreModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                coreModule
            )
            androidContext(this@PokeApp)
        }
    }
}