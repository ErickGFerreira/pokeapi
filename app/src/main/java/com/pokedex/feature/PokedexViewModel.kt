package com.pokedex.feature

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.pokedex.domain.model.Pokemon
import com.pokedex.feature.PokedexViewModel.FlowData.Companion.SAVED_STATE_KEY
import com.pokedex.utils.viewmodel.BaseFlowData
import com.pokedex.utils.viewmodel.FlowDataSavedStateHandle
import com.pokedex.utils.viewmodel.SavedStateViewModelAssistedFactory
import javax.inject.Inject

class PokedexViewModelFactory @Inject constructor(
    private val navigationEvent: PokedexUiEvent,
) : SavedStateViewModelAssistedFactory<PokedexViewModel> {
    override fun create(handle: SavedStateHandle) =
        PokedexViewModel(
            handle = FlowDataSavedStateHandle(handle = handle, key = SAVED_STATE_KEY),
            uiEvent = navigationEvent,
        )
}

class PokedexViewModel @Inject constructor(
    private val handle: FlowDataSavedStateHandle<FlowData>,
    val uiEvent: PokedexUiEvent,
) : ViewModel() {
    var flowData = FlowData()

    fun setup() {
    }

    fun startDestination(): PokedexUiEvent.Navigation =
        PokedexUiEvent.Navigation.PokemonList

    fun navigate(navigation: PokedexUiEvent.Navigation) {
        uiEvent.send(event = navigation)
    }

    data class FlowData(
        var pokemons: MutableList<Pokemon> = mutableListOf()
    ) : BaseFlowData {
        companion object {
            const val SAVED_STATE_KEY = "[Pokedex.FlowData.SavedState]"
        }
    }
}
