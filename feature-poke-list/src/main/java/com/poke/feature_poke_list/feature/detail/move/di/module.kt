package com.poke.feature_poke_list.feature.detail.move.di

import com.poke.feature_poke_list.feature.detail.move.domain.MoveUseCase
import com.poke.feature_poke_list.feature.detail.move.presentation.MoveViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moveModule = module {
    viewModel { parameters ->
        MoveViewModel(useCase = get(), pokeId = parameters.get())
    }

    factory {
        MoveUseCase(
            repository = get()
        )
    }
}