package com.josh.obesityapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.josh.obesityapp.ui.theme.customLightBrown

@Composable
fun CustomSurface(
    content: @Composable () -> Unit
){
    Surface(
        modifier = Modifier.fillMaxWidth().padding(8 .dp),
        shape = RoundedCornerShape(15 .dp),
        color = customLightBrown,
        content = content
    )
}