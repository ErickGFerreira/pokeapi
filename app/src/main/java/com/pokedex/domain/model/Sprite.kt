package com.pokedex.domain.model

data class Sprite(
    val frontDefault: String,
){
    companion object {
        fun mock() = Sprite(
            frontDefault = "front_default"
        )
    }
}