package com.poke.feature_poke_list.feature.detail.stats

import com.poke.feature_poke_list.feature.detail.stats.domain.StatsUseCase
import com.poke.feature_poke_list.feature.detail.stats.presentation.StatsViewModel
import org.koin.android.viewmodel.dsl.viewModel
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