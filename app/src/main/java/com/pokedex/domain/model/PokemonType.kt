package com.pokedex.domain.model

data class PokemonType(
    val type: PokemonAddr
) {
    companion object {
        fun mock() =
            PokemonType(
               type = PokemonAddr.mock()
            )
    }
}