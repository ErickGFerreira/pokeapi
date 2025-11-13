package com.pokedex.data.response

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    @SerializedName("types") val type: List<PokemonTypeResponse>,
    @SerializedName("sprites") val sprites: SpriteResponse
) {
    companion object {
        fun mock() =
            PokemonDetailResponse(
                type = listOf(
                    PokemonTypeResponse(
                        PokemonAddrResponse.mock()
                    )
                ),
                sprites = SpriteResponse.mock()
            )
    }
}