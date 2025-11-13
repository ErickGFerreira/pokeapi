package com.pokedex.utils.security

object SecurityEndpoints {

    object PokedexBackend {
        const val API = "https://pokeapi.co/api/v2/"
        const val POKEMONS = "$API/v3/auth/login"
        const val POKEMONS_DETAILS = "$API/v1/auth/login/challenge"
    }
}