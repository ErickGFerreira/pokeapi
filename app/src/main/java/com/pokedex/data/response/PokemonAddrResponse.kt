package com.pokedex.data.response

import com.google.gson.annotations.SerializedName

data class PokemonAddrResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
) {
    companion object {
        fun mock() =
            PokemonAddrResponse(
                name = "name",
                url = "url",
            )
    }
}