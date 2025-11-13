package com.pokedex.utils.state

import com.pokedex.utils.error.Error

sealed interface ScreenState {
    data object ScreenContent : ScreenState
    data object ScreenProgress : ScreenState
    data class ScreenError(val error: Error) : ScreenState
}