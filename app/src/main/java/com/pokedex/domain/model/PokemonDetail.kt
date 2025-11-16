package com.pokedex.domain.model

data class PokemonDetail(
    val types: List<PokemonType>,
    val sprites: Sprite
) {
    companion object {
        fun mock() =
            PokemonDetail(
                types = listOf(),
                sprites = Sprite.mock()
            )
    }
}