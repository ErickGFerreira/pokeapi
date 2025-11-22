package com.pokedex.feature.pokemons

sealed class PokemonListScreenAction {
    data object ErrorButtonAction : PokemonListScreenAction()

    data object ErrorCloseButtonAction : PokemonListScreenAction()

    data object ErrorPaginationButtonAction : PokemonListScreenAction()
    data object GoToPokemonDetailAction : PokemonListScreenAction()

    data object CloseButtonAction : PokemonListScreenAction()

    data object PaginateAction : PokemonListScreenAction()

    data object OnToastDismissedAction : PokemonListScreenAction()

}

@Suppress
fun PokemonListScreenAction.fold(
    errorButtonAction: () -> Unit,
    errorCloseButtonAction: () -> Unit,
    errorPaginationButtonAction: () -> Unit,
    goToPokemonDetailAction: () -> Unit,
    closeButtonAction: () -> Unit,
    paginateAction: () -> Unit,
    onToastDismissedAction: () -> Unit,
): Unit =
    when (this) {
        PokemonListScreenAction.ErrorCloseButtonAction -> errorCloseButtonAction()
        PokemonListScreenAction.ErrorButtonAction -> errorButtonAction()
        PokemonListScreenAction.CloseButtonAction -> closeButtonAction()
        PokemonListScreenAction.ErrorPaginationButtonAction -> errorPaginationButtonAction()
        PokemonListScreenAction.GoToPokemonDetailAction -> goToPokemonDetailAction()
        PokemonListScreenAction.PaginateAction -> paginateAction()
        PokemonListScreenAction.OnToastDismissedAction -> onToastDismissedAction()
    }