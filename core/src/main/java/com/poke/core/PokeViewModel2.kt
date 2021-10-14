package com.poke.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poke.core.data.database.model.Poke
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PokeViewModel2(
    private val getPokesUseCase: GetPokesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<List<Poke>>(emptyList())
    val state: StateFlow<List<Poke>> get() = _state

    init {
        viewModelScope.launch {
            getPokesUseCase(10).collect {
                _state.emit(it)
            }
        }
    }

}