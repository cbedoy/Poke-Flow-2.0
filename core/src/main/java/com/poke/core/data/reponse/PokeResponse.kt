package com.poke.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokeResponse(
    val results: List<PokeItem> = emptyList()
)

@Serializable
data class PokeItem(
    val name: String = "",
    val url: String = ""
)

@Serializable
data class PokeSpecieResponse(
    val id: String? = null,
    @SerialName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntriesResponse> = emptyList()
)

@Serializable
data class FlavorTextEntriesResponse(
    @SerialName("flavor_text")
    val flavorText: String? = null
)

@Serializable
data class PokeColorResponse(
    val id: String? = null,
    val name: String? = null
)

@Serializable
data class PokeItemResponse(
    val id: Long? = null,
    val name: String? = "",
    val url: String? = "",
    val weight: Int? = 0,
    val sprites: PokeSpriteResponse? = PokeSpriteResponse(),
    val types: List<PokeTypesResponse>? = emptyList(),
    val moves: List<PokeMovesResponse>? = emptyList(),
    val abilities: List<PokeAbilitiesResponse>? = emptyList(),
)

@Serializable
data class PokeAbilitiesResponse(
    val ability: PokeAbilityResponse? = PokeAbilityResponse()
)

@Serializable
data class PokeAbilityResponse(
    val name: String? = ""
)

@Serializable
data class PokeSpriteResponse(
    @SerialName("front_default")
    val avatar: String? = "",
    val other: PokeOtherResponse? = null
)

@Serializable
data class PokeOtherResponse(
    @SerialName("dream_world")
    val dreamWorld: DreamWorldResponse? = null
)

@Serializable
data class DreamWorldResponse(
    @SerialName("front_default")
    val avatar: String? = ""
)

@Serializable
data class PokeMovesResponse(
    val move: PokeMoveResponse? = PokeMoveResponse()
)

@Serializable
data class PokeMoveResponse(
    val name: String? = ""
)

@Serializable
data class PokeTypesResponse(
    val type: PokeTypeResponse? = PokeTypeResponse()
)

@Serializable
data class PokeTypeResponse(
    val name: String? = ""
)