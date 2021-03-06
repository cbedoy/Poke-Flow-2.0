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
    @SerialName("base_experience") val experience: Int? = 0,
    val types: List<PokeTypesResponse>? = emptyList(),
    val moves: List<PokeMovesResponse>? = emptyList(),
    val stats: List<PokeStatResponse>? = emptyList(),
    val abilities: List<PokeAbilitiesResponse>? = emptyList(),
)

@Serializable
data class PokeStatResponse(
    @SerialName("base_stat") val baseStat: Int? = 0,
    val stat: PokeStatItemResponse? = null
)

@Serializable
data class PokeStatItemResponse(
    val name: String? = null,
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