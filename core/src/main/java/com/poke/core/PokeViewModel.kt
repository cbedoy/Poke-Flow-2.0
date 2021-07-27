package com.poke.core

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import com.poke.core.data.local.LocalSource
import com.poke.core.data.repo.PokeRepository

class PokeViewModel (private val localSource: LocalSource): ViewModel() {

    val pokeLiveData
        get() = localSource.pokeLiveData

}