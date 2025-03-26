package com.josh.obesityapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.josh.obesityapp.ui.theme.customBrown
import com.josh.obesityapp.ui.theme.customDarkGreen

@Composable
fun CustomHorizontalDivider(){
    Box (
        modifier = Modifier
            .width(180 .dp)
            .height(2 .dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(customBrown, customDarkGreen)
                )
            )
    )
}
@Composable
fun FillWidthHorizontalDivider(){
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4 .dp)
            .height(1 .dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(customBrown, customDarkGreen)
                )
            )
    )
}