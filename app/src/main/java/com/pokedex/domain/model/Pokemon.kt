package com.pokedex.domain.model

data class Pokemon(
    val name: String,
    val imageUrl: String,
    val type: List<PokemonAddr>,
) {
    companion object {
        fun mockList() = listOf(
            Pokemon(
                name = "name",
                imageUrl = "front_default",
                type = PokemonAddr.mockList()
            ),
            Pokemon(
                name = "name",
                imageUrl = "front_default",
                type = PokemonAddr.mockList()
            )
        )
    }
}