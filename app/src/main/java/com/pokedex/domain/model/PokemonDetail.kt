package com.pokedex.domain.model

data class PokemonDetail(
    val id: Int,
    val types: List<PokemonType>,
    val sprites: Sprite
) {
    companion object {
        fun mock() =
            PokemonDetail(
                id = 0,
                types = PokemonType.mockList(),
                sprites = Sprite.mock()
            )
    }
}