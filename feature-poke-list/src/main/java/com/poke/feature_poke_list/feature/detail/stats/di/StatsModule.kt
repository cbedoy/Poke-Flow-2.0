package com.poke.feature_poke_list.feature.detail.stats.di

import com.poke.feature_poke_list.feature.detail.stats.domain.StatsUseCase
import com.poke.feature_poke_list.feature.detail.stats.presentation.StatsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val statsModule = module {
    viewModel { parameters ->
        StatsViewModel(useCase = get(), pokeId = parameters.get())
    }

    factory {
        StatsUseCase(
            repository = get()
        )
    }
}