package com.poke.pagination

import android.app.Application
import coil.ImageLoader
import coil.decode.SvgDecoder
import com.poke.core.di.coreModule
import com.poke.feature_poke_list.feature.detail.abilitity.di.abilityModule
import com.poke.feature_poke_list.feature.detail.move.di.moveModule
import com.poke.feature_poke_list.feature.detail.stats.statsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                coreModule, statsModule, abilityModule, moveModule, module
            )
            androidContext(this@PokeApp)
        }
    }
}