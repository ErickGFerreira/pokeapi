package com.pokedex.domain.model

data class PokemonAddr(
    val name: String,
    val url: String,
) {
    companion object {
        fun mock() =
            PokemonAddr(
                name = "name",
                url = "url",
            )

        fun mockList() = listOf(
            PokemonAddr(
                name = "name",
                url = "url",
            ),
            (PokemonAddr(
                name = "name",
                url = "url",
            ))
        )
    }
}