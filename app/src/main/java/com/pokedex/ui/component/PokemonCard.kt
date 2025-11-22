package com.pokedex.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import coil3.compose.AsyncImage
import com.pokedex.ui.dimen.FontSize
import com.pokedex.ui.dimen.Size
import com.pokedex.ui.dimen.Spacing

@Composable
fun PokemonCard(
    modifier: Modifier = Modifier,
    name: String,
    types: List<String>,
    imageUrl: String,
) {
    Row(
        modifier = modifier
            .padding(horizontal = Spacing.SM)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        AsyncImage(
            modifier = Modifier
                .wrapContentHeight()
                .height(Size.size84),
            model = imageUrl,
            contentDescription = "Image",
            contentScale = ContentScale.Fit
        )
        Text(
            fontSize = FontSize.MD1,
            style = TextStyle(
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            ),
            text = name
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.MS),
            modifier = Modifier.padding(Spacing.XS)
        ) {
            types.forEach { type ->
                TypeBadge(
                    type = type
                )
            }
        }
    }
}
