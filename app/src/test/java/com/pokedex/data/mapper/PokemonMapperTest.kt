package com.pokedex.data.mapper

import com.pokedex.data.mapper.PokemonMapper.toPokemonAddr
import com.pokedex.data.response.PokemonAddrResponse
import com.pokedex.domain.model.PokemonAddr
import org.junit.Assert.assertEquals
import org.junit.Test

class PokemonMapperTest {
    @Test
    fun `given a pokemon addr response, when to pokemon addr called, then map to pokemon addr`() {
        // given
        val response = PokemonAddrResponse.mock()
        val expectedResult = PokemonAddr.mock()

        // when
        val result = response.toPokemonAddr()

        // then
        assertEquals(expectedResult, result)
    }
}
