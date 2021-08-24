package com.poke.feature_poke_list.feature.detail.stats.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poke.core.data.database.model.Stat
import com.poke.feature_poke_list.feature.detail.stats.domain.StatsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StatsViewModel (private val useCase: StatsUseCase, private val pokeId: Long): ViewModel(){

    private val _state = MutableStateFlow<List<Stat>>(emptyList())
    val state : StateFlow<List<Stat>>
        get() = _state

    init {
        viewModelScope.launch {
            useCase.getPokeStats(pokeId).collectLatest {
                _state.value = it
            }
        }
    }
}