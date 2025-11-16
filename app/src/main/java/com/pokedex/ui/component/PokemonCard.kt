package com.pokedex.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import com.pokedex.ui.dimen.FontSize
import com.pokedex.ui.dimen.Size
import com.pokedex.ui.dimen.SpacerVertical
import com.pokedex.ui.dimen.Spacing
import com.pokedex.ui.theme.Typography
import org.intellij.lang.annotations.JdkConstants

@Composable
fun PokemonCard(
    modifier: Modifier = Modifier,
    bordercolor: Color,
    name: String,
    types: String,
    imageUrl: String,
) {
    Card(
        modifier = modifier
            .background(Color.Transparent),
        border = BorderStroke(2.dp, bordercolor)
    ) {
        Box {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .aspectRatio(2F),
                model = imageUrl,
                contentDescription = "Image",
                contentScale = ContentScale.Fit
            )
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
                .align(Alignment.End)
                .padding(all = Spacing.SM),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                fontSize = FontSize.MD2,
                color = Color.White,
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                text = name
            )
            Text(
                fontSize = FontSize.MD2,
                color = Color.White,
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                text = types,
            )
        }
    }
}
