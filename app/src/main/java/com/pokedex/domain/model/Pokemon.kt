package com.pokedex.domain.model

import androidx.compose.ui.text.font.Typeface

data class Pokemon(
    val name: String,
    val imageUrl: String,
    val type: String,
) {
    companion object {
        fun mockList() = listOf(
            Pokemon(
                name = "name",
                imageUrl = "front_default",
                type = "name"
            ),
            Pokemon(
                name = "name",
                imageUrl = "front_default",
                type = "name"
            )
        )
    }
}