package com.pokedex.domain.model

import com.google.gson.annotations.SerializedName

data class Sprite(
    val frontDefault: String,
){
    companion object {
        fun mock() = Sprite(
            frontDefault = "front_default"
        )
    }
}