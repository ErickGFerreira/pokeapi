package com.pokedex.data.response

import com.google.gson.annotations.SerializedName


data class SpriteResponse(
    @SerializedName("front_default") val frontDefault: String,
) {
    companion object {
        fun mock() = SpriteResponse(
            frontDefault = "front_default"
        )
    }
}