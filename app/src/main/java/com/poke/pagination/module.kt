package com.poke.pagination

import coil.ImageLoader
import coil.decode.SvgDecoder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val module = module {
    single {
        ImageLoader.Builder(context = androidContext())
            .componentRegistry {
                add(SvgDecoder(context = androidContext()))
            }.build()
    }
}