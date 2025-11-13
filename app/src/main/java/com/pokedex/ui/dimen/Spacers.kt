package com.pokedex.ui.dimen

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun SpacerVertical(dp: Dp = Spacing.SM) {
    Spacer(modifier = Modifier.height(dp))
}

@Composable
fun ColumnScope.SpacerWeight() {
    Spacer(modifier = Modifier.weight(Weight1))
}

@Composable
fun RowScope.SpacerWeight() {
    Spacer(modifier = Modifier.weight(Weight1))
}

@Composable
fun SpacerHorizontal(dp: Dp = Spacing.SM) {
    Spacer(modifier = Modifier.width(dp))
}

@Composable
fun ColumnScope.SpacerWeight(weight: Float = Weight1) {
    Spacer(modifier = Modifier.weight(weight))
}

@Composable
fun RowScope.SpacerWeight(weight: Float = Weight1) {
    Spacer(modifier = Modifier.weight(weight))
}
