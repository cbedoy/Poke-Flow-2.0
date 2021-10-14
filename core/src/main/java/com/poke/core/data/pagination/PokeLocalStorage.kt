package com.poke.core.data.pagination

import com.poke.core.PokeItemResponse
import com.poke.core.data.database.model.Poke

interface PokeLocalStorage {

    val getPokeCount: Int

    val currentPokes: List<Poke>

    fun savePoke(response: PokeItemResponse)
}