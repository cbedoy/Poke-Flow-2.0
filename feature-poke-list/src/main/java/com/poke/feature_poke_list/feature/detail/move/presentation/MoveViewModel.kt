package com.poke.feature_poke_list.feature.detail.move.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poke.core.data.database.model.Move
import com.poke.feature_poke_list.feature.detail.move.domain.MoveUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MoveViewModel (private val useCase: MoveUseCase, private val pokeId: Long): ViewModel(){

    private val _state = MutableStateFlow<List<Move>>(emptyList())
    val state : StateFlow<List<Move>>
        get() = _state

    init {
        viewModelScope.launch {
            useCase.getPokeMoves(pokeId).collectLatest {
                _state.value = it
            }
        }
    }
}