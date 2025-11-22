package com.pokedex.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.pokedex.ui.dimen.Spacing
import com.pokedex.ui.theme.colorBlue
import com.pokedex.ui.theme.colorBrown
import com.pokedex.ui.theme.colorGreen
import com.pokedex.ui.theme.colorRed
import com.pokedex.ui.theme.colorYellow

@Composable
fun TypeBadge(
    modifier: Modifier = Modifier,
    type: String,
) {
    Row(
        modifier = modifier
            .width(70.dp)
            .background(
                getBackgroundColor(
                    BadgePokemonType.fromName(type.uppercase())
                )
            )
            .padding(all = Spacing.S),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = type,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold
            ),
            overflow = TextOverflow.Ellipsis,
        )
    }
}

fun getBackgroundColor(pokemonType: BadgePokemonType): Color {
    return when (pokemonType) {
        BadgePokemonType.FIRE -> {
            colorRed
        }

        BadgePokemonType.WATER -> {
            colorBlue
        }

        BadgePokemonType.GRASS -> {
            colorGreen
        }

        BadgePokemonType.POISON -> {
            Color.Magenta
        }

        BadgePokemonType.ELECTRIC ->
            colorYellow

        else -> {
            colorBrown
        }
    }
}

enum class BadgePokemonType {
    FIRE, WATER, GRASS, POISON, ELECTRIC, NORMAL;

    companion object {
        fun fromName(name: String) = runCatching {
            BadgePokemonType.valueOf(name)
        }.getOrNull() ?: NORMAL
    }
}