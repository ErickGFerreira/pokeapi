package com.pokedex.data.mapper

import com.pokedex.data.response.PokemonAddrResponse
import com.pokedex.data.response.PokemonDetailResponse
import com.pokedex.data.response.PokemonTypeResponse
import com.pokedex.data.response.PokemonsListResponse
import com.pokedex.data.response.SpriteResponse
import com.pokedex.domain.model.PokemonAddr
import com.pokedex.domain.model.PokemonDetail
import com.pokedex.domain.model.PokemonList
import com.pokedex.domain.model.PokemonType
import com.pokedex.domain.model.Sprite

object PokemonMapper {
    fun PokemonsListResponse.toPokemonList() =
        PokemonList(
            results = results.map { it.toPokemonAddr() }
        )

    fun PokemonAddrResponse.toPokemonAddr() =
        PokemonAddr(
            name = name,
            url = url,
        )

    fun PokemonDetailResponse.toPokemonDetail() =
        PokemonDetail(
            type = type.map { it.toPokemonType() },
            sprites = sprites.toSprite(),
        )

    fun PokemonTypeResponse.toPokemonType() =
        PokemonType(
            type = type.toPokemonAddr()
        )

    fun SpriteResponse.toSprite() =
        Sprite(
            frontDefault = frontDefault
        )
}