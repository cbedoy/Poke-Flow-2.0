package com.poke.feature_poke_list.feature.detail.abilitity.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poke.core.data.database.model.Ability
import com.poke.feature_poke_list.feature.detail.abilitity.domain.AbilityUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AbilityViewModel (private val useCase: AbilityUseCase, private val pokeId: Long): ViewModel(){

    private val _state = MutableStateFlow<List<Ability>>(emptyList())
    val state : StateFlow<List<Ability>>
        get() = _state

    init {
        viewModelScope.launch {
            useCase.getPokeAbility(pokeId).collectLatest {
                _state.value = it
            }
        }
    }
}