package com.pokedex.data.response

import com.google.gson.annotations.SerializedName

data class PokemonsListResponse(
    @SerializedName("results") val results: List<PokemonAddrResponse>,
) {
    companion object {

        fun mock() =
            PokemonsListResponse(
                results = listOf(
                    PokemonAddrResponse(
                        name = "name",
                        url = "url"
                    ),
                    PokemonAddrResponse(
                        name = "name",
                        "url"
                    ),
                )
            )
    }
}