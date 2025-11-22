package com.pokedex.feature.pokemons

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pokedex.feature.PokedexViewModel
import com.pokedex.feature.pokemons.PokemonsListUiEvent.Event.Finish
import com.pokedex.feature.pokemons.PokemonsListUiEvent.Event.NavigateToPokemonDetail
import com.pokedex.ui.component.AppToast
import com.pokedex.ui.component.PokemonCard
import com.pokedex.ui.component.ScreenError
import com.pokedex.ui.dimen.Size
import com.pokedex.utils.GENERIC_HTTP_ERROR
import com.pokedex.utils.view.ScreenScaffold
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

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
        error = {
            ScreenError(
                title = it.title,
                message = it.message,
                onTryAgainClick = { onActionEvent(PokemonListScreenAction.ErrorButtonAction) },
                onCloseClick = { onActionEvent(PokemonListScreenAction.ErrorCloseButtonAction) }
            )
        },
        content = {
            ScreenContent(
                onActionEvent = onActionEvent,
                uiState = uiState
            )
        })
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
    val presentation by uiState.screenPresentation.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            TrackingCardList(
                presentation = presentation,
                onActionEvent = onActionEvent,
            )
            AppToast(
                modifier = Modifier.align(Alignment.BottomCenter),
                message = presentation.error?.title ?: GENERIC_HTTP_ERROR,
                visible = presentation.errorPagination,
                onAction = { onActionEvent(PokemonListScreenAction.ErrorPaginationButtonAction) },
                onDismiss = { onActionEvent(PokemonListScreenAction.OnToastDismissedAction) }
            )
        }
    }
}

@Composable
private fun TrackingCardList(
    presentation: PokemonListUiState.Presentation,
    onActionEvent: (PokemonListScreenAction) -> Unit
) {
    val lazyListState = rememberLazyListState()

    val shouldPaginate = remember {
        derivedStateOf {
            val totalItems = lazyListState.layoutInfo.totalItemsCount
            val lastVisibleItemIndex =
                lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastVisibleItemIndex == totalItems - 1
        }
    }

    LaunchedEffect(key1 = lazyListState) {
        snapshotFlow { shouldPaginate.value }
            .distinctUntilChanged()
            .filter { it }
            .collect { onActionEvent(PokemonListScreenAction.PaginateAction) }
    }
    if (presentation.isPaginating) {
        CircularProgressIndicator()
    }
    LazyColumn(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.statusBars)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = lazyListState
    ) {
        items(
            items = presentation.pokemons,
            key = { pokemon -> pokemon.id }) { pokemonCard ->
            if (pokemonCard.id == 1) {
                HorizontalDivider(color = Color.Black, thickness = Size.Size1)
            }
            PokemonCard(
                modifier = Modifier
                    .background(Color.Transparent),
                name = pokemonCard.name,
                types = pokemonCard.type.map { it.name },
                imageUrl = pokemonCard.imageUrl,
            )
            HorizontalDivider(color = Color.Black, thickness = Size.Size1)
        }
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    Screen(
        onActionEvent = {},
        uiState = PokemonListUiState().apply {
        }
    )
}
