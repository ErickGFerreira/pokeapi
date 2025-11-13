package com.pokedex.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import com.pokedex.ui.dimen.Size
import com.pokedex.ui.dimen.SpacerVertical
import com.pokedex.ui.theme.Typography

@Composable
fun PokemonCard(
    modifier: Modifier = Modifier,
    name: String,
    type:String,
    imageUrl: String,
) {
    Column(
        modifier = Modifier
            .background(Color.Blue)
            .padding(Size.SizeSSM)
    ) {
        Box(
            modifier = modifier.background(
                color = Color.Red,
                shape = CircleShape
            ),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(data = imageUrl)
                    .transformations(CircleCropTransformation())
                    .build(),
                contentDescription = null,
            )
        }
        Text(
            modifier = Modifier.testTag(tag = "TEXT"),
            text = name,
            color = Color.Black,
            style = Typography.labelLarge,
        )
        SpacerVertical(dp = Size.Size8)
        Text(
            modifier = Modifier.testTag(tag = "TEXT"),
            text = type,
            color = Color.Black,
            style = Typography.labelLarge,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AvatarImagePreview() {
    PokemonCard(
        modifier = Modifier.size(Size.SizeXLG),
        name = "ERICK",
        type = "GRASS",
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
    )
}
