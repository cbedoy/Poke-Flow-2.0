package com.poke.feature_poke_list.feature.detail.abilitity.di

import com.poke.feature_poke_list.feature.detail.abilitity.domain.AbilityUseCase
import com.poke.feature_poke_list.feature.detail.abilitity.presentation.AbilityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val abilityModule = module {
    viewModel { parameters ->
        AbilityViewModel(useCase = get(), pokeId = parameters.get())
    }

    factory {
        AbilityUseCase(
            repository = get()
        )
    }
}