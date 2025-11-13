package com.pokedex.utils.view

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.pokedex.ui.theme.PokedexTheme
import com.pokedex.utils.error.Error
import com.pokedex.utils.state.ScreenState

val DefaultSurfaceColor = Color.White

@Composable
fun ScreenScaffold(
    state: ScreenState = ScreenState.ScreenContent,
    surfaceColor: Color = DefaultSurfaceColor,
    progress: @Composable () -> Unit = {},
    error: @Composable (Error) -> Unit = {},
    content: @Composable () -> Unit,
) = PokedexTheme {
    Surface(color = surfaceColor) {
        when (state) {
            is ScreenState.ScreenError -> error(state.error)
            ScreenState.ScreenProgress -> progress()
            ScreenState.ScreenContent -> content()
        }
    }
}
