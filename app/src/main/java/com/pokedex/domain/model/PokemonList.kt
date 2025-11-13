package com.pokedex.domain.model

data class PokemonList(
    val results: List<PokemonAddr>
) {
    companion object {

        fun mock() =
            PokemonList(
                results = listOf(
                    PokemonAddr(
                        name = "name",
                        url = "url"
                    ),
                    PokemonAddr(
                        name = "name",
                        "url"
                    ),
                )
            )
    }
}