package com.pokedex.domain.model

enum class PokemonTypeEnum() {
    FIRE, WATER, GRASS, NORMAL, POISON;

    companion object {
        fun fromName(name: String) = runCatching {
            PokemonTypeEnum.valueOf(name)
        }.getOrNull() ?: NORMAL
    }
}