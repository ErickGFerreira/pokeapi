package com.pokedex.feature.pokemons

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pokedex.feature.PokedexViewModel
import com.pokedex.feature.pokemons.PokemonsListUiEvent.Event.Finish
import com.pokedex.feature.pokemons.PokemonsListUiEvent.Event.NavigateToPokemonDetail
import com.pokedex.ui.component.PokemonCard
import com.pokedex.ui.component.ScreenError
import com.pokedex.ui.dimen.SpacerVertical
import com.pokedex.ui.dimen.Spacing
import com.pokedex.utils.view.ScreenScaffold

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel,
    flowData: PokedexViewModel.FlowData,
    navigateToPokemonDetails: () -> Unit,
) {
    val activity = LocalActivity.current as FragmentActivity

    LaunchedEffect(key1 = Unit) {
        viewModel.setup(flowData = flowData)
    }
    Screen(
        uiState = viewModel.uiState,
        onActionEvent = viewModel::onActionEvent,
    )
    EventConsumer(
        activity = activity,
        viewModel = viewModel,
        navigateToPokemonDetails = navigateToPokemonDetails,
    )
}

@Composable
private fun EventConsumer(
    activity: FragmentActivity,
    viewModel: PokemonListViewModel,
    navigateToPokemonDetails: () -> Unit,
) = LaunchedEffect(key1 = Unit) {
    viewModel.uiEvent.collect { event ->
        when (event) {
            Finish -> activity.finish()
            is NavigateToPokemonDetail -> navigateToPokemonDetails()
        }
    }
}

@Composable
private fun Screen(
    onActionEvent: (PokemonListScreenAction) -> Unit,
    uiState: PokemonListUiState,
) {
    ScreenScaffold(
        state = uiState.screenState.collectAsStateWithLifecycle().value,
        progress = { ScreenProgress() },
        error = { error ->
            ScreenError(
                onTryAgainClick = { onActionEvent(PokemonListScreenAction.ErrorButtonAction) },
                onCloseClick = { onActionEvent(PokemonListScreenAction.ErrorCloseButtonAction) },
                title = error.title,
                message = error.message
            )
        },
        content = {
            ScreenContent(
                uiState = uiState,
                onActionEvent = onActionEvent,
            )
        },
    )
}

@Composable
private fun ScreenProgress() {
    Column(
        modifier =
            Modifier
                .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenContent(
    onActionEvent: (PokemonListScreenAction) -> Unit,
    uiState: PokemonListUiState
) {
    TrackingCardList(
        uiState = uiState,
        onActionEvent = onActionEvent,
    )
}

@Composable
private fun TrackingCardList(
    uiState: PokemonListUiState,
    onActionEvent: (PokemonListScreenAction) -> Unit
) {
    val cardsPresentation by uiState.cardsPresentation.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.statusBars)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = lazyListState
    ) {
        item { SpacerVertical(Spacing.XS) }
        items(
            items = cardsPresentation,
            key = { pokemon -> pokemon.id }) { pokemonCard ->
            PokemonCard(
                modifier = Modifier
                    .padding(horizontal = Spacing.MD)
                    .background(Color.Transparent),
                name = pokemonCard.name,
                types = pokemonCard.type,
                imageUrl = pokemonCard.imageUrl,
                borderColor = pokemonCard.borderColor,
            )
            SpacerVertical(Spacing.SM)
        }
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    Screen(
        onActionEvent = {},
        uiState = PokemonListUiState().apply {
            showProgress()
        }
    )
}
