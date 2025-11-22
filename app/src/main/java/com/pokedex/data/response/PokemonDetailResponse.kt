package com.pokedex.data.response

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("types") val types: List<PokemonTypeResponse>,
    @SerializedName("sprites") val sprites: SpriteResponse
) {
    companion object {
        fun mock() =
            PokemonDetailResponse(
                id = 0,
                types = PokemonTypeResponse.mockList(),
                sprites = SpriteResponse.mock()
            )
    }
}