package com.poke.core.data.pagination

import com.poke.core.PokeItemResponse

interface PokeLocalStorage {

    val getPokeCount: Int

    fun savePoke(response: PokeItemResponse)
}