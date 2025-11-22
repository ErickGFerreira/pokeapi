package com.pokedex.domain.model

data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val type: List<PokemonAddr>,
) {
    companion object {
        fun mockList() = listOf(
            Pokemon(
                id = 0,
                name = "name",
                imageUrl = "front_default",
                type = PokemonAddr.mockList()
            ),
            Pokemon(
                id = 1,
                name = "name",
                imageUrl = "front_default",
                type = PokemonAddr.mockList()
            )
        )
    }
}