package com.pokedex.feature

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.pokedex.PokedexModule
import com.pokedex.utils.navigatin.setNavigationContent
import com.pokedex.utils.view.BaseComposeActivity
import com.pokedex.utils.viewmodel.SavedStateViewModelFactory
import javax.inject.Inject
import com.pokedex.feature.PokedexUiEvent.Navigation.PokemonList
import com.pokedex.feature.PokedexUiEvent.Navigation.Finish
import com.pokedex.feature.pokemons.PokemonListScreen
import com.pokedex.utils.navigatin.customNavigate


class PokedexActivity : BaseComposeActivity<PokedexViewModel>() {
    @Inject
    lateinit var pokedexViewModelFactory: PokedexViewModelFactory

    override fun inject() = PokedexModule.component.inject(activity = this)

    override fun viewModelClass() = PokedexViewModel::class.java

    override fun createFlowViewModelFactory(): ViewModelProvider.Factory =
        SavedStateViewModelFactory(
            viewModelFactory = pokedexViewModelFactory,
            owner = this,
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState = savedInstanceState)
        flowViewModel.setup()
        setNavigationContent(
            startDestination = flowViewModel.startDestination().route,
            navGraphBuilder = this::navGraphBuilder,
            navEventFlow = flowViewModel.uiEvent.eventsFlow,
            navEvent = this::navEvent,
        )
    }

    @Suppress
    private fun navGraphBuilder(builder: NavGraphBuilder) =
        builder.apply {
            composable(route = PokemonList.route) {
                PokemonListScreen(
                    viewModel = composeViewModel(),
                    flowData = flowViewModel.flowData,
                    navigateToPokemonDetails = {}
                )
            }
        }

    private fun navEvent(
        navController: NavHostController,
        navScreen: PokedexUiEvent.Navigation,
    ) {
        when (navScreen) {
            is Finish -> finish()
            else -> navController.customNavigate(route = navScreen.route)
        }
    }

}