package com.pokedex.feature.pokemons

sealed class PokemonListScreenAction {
    data object ErrorButtonAction : PokemonListScreenAction()

    data object ErrorCloseButtonAction : PokemonListScreenAction()

    data object GoToPokemonDetailAction : PokemonListScreenAction()

    data object CloseButtonAction : PokemonListScreenAction()

}

@Suppress
fun PokemonListScreenAction.fold(
    errorButtonAction: () -> Unit,
    errorCloseButtonAction: () -> Unit,
    goToPokemonDetailAction: () -> Unit,
    closeButtonAction: () -> Unit,
): Unit =
    when (this) {
        PokemonListScreenAction.ErrorCloseButtonAction -> errorCloseButtonAction()
        PokemonListScreenAction.ErrorButtonAction -> errorButtonAction()
        PokemonListScreenAction.GoToPokemonDetailAction -> goToPokemonDetailAction()
        PokemonListScreenAction.CloseButtonAction -> closeButtonAction()
    }