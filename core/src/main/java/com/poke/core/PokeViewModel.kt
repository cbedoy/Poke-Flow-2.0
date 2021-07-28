package com.poke.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.poke.core.data.database.model.Poke
import com.poke.core.data.local.LocalSource

class PokeViewModel (private val localSource: LocalSource): ViewModel() {

    var pokeLiveData: LiveData<PagedList<Poke>> = localSource.pokeLiveData

    fun onTextChange(onText: String?) {
        pokeLiveData = if (onText.isNullOrEmpty() || onText.isNullOrBlank()) {
            localSource.pokeLiveData
        } else {
            localSource.queryPokeLiveData(onText)
        }
    }

}