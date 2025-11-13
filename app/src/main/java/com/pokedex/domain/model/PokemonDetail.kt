package com.pokedex.domain.model

data class PokemonDetail(
    val type: List<PokemonType>,
    val sprites: Sprite
) {
    companion object {
        fun mock() =
            PokemonDetail(
                type = listOf(
                    PokemonType(
                        PokemonAddr.mock()
                    )
                ),
                sprites = Sprite.mock()
            )
    }
}