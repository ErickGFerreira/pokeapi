package com.pokedex.feature.pokemons

import androidx.compose.ui.graphics.Color
import com.pokedex.domain.model.Pokemon
import com.pokedex.domain.model.PokemonAddr
import com.pokedex.domain.model.PokemonTypeEnum
import com.pokedex.utils.state.ScreenState
import com.pokedex.utils.error.Error
import io.mockk.InternalPlatformDsl.toStr
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class PokemonListUiState @Inject constructor() {
    val screenState = MutableStateFlow<ScreenState>(value = ScreenState.ScreenProgress)
    val cardsPresentation = MutableStateFlow<List<CardPresentation>>(value = emptyList())


    fun showScreen(
        pokemonList: List<Pokemon>,
    ) {
        setUpPokemonList(pokemonList = pokemonList)
        showContent()
    }

    fun showError(error: Error) {
        screenState.value = ScreenState.ScreenError(error = error)
    }

    fun showProgress() {
        screenState.value = ScreenState.ScreenProgress
    }

    private fun showContent() {
        screenState.value = ScreenState.ScreenContent
    }

    private fun Pokemon.toCardPresentation(id: Int) = CardPresentation(
        id = id,
        name = name,
        imageUrl = imageUrl,
        borderColor = getBorderColor(
            type = PokemonTypeEnum.fromName(name = type.first().name.uppercase())
        ),
        type = getPokemonTypes(types = type),
    )

    private fun getBorderColor(type: PokemonTypeEnum): Color {
        return when (type) {
            PokemonTypeEnum.FIRE -> Color.Red
            PokemonTypeEnum.WATER -> Color.Blue
            PokemonTypeEnum.GRASS -> Color.Green
            PokemonTypeEnum.POISON -> Color.Magenta
            else -> Color.Gray
        }
    }

    private fun getPokemonTypes(types: List<PokemonAddr>): String {
        val builder = StringBuilder()
        types.forEachIndexed { index, type ->
            if (index == types.size - 1) {
                builder.append(type.name)
            } else builder.append("${type.name}/")
        }
        return builder.toString()
    }

    private fun setUpPokemonList(
        pokemonList: List<Pokemon>
    ) {
        cardsPresentation.value = pokemonList.mapIndexed { id, pokemon ->
            pokemon.toCardPresentation(id = id)
        }
    }

    data class CardPresentation(
        val id: Int,
        val name: String,
        val type: String,
        val imageUrl: String,
        val borderColor: Color = Color.Gray
    )
}