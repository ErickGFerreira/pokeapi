package com.pokedex.data.response

import com.google.gson.annotations.SerializedName

data class PokemonTypeResponse(
    @SerializedName("type") val type: PokemonAddrResponse,
)