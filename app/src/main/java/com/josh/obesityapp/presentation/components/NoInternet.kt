package com.josh.obesityapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josh.obesityapp.R
import com.josh.obesityapp.ui.theme.customBrown
import com.josh.obesityapp.ui.theme.customDarkGreen

@Composable
fun NoInternet() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val gradientBrush = Brush.linearGradient(
            colors = listOf(customDarkGreen, customBrown)
        )
        Image(
            painter = painterResource(R.drawable.no_wifi),
            contentDescription = stringResource(R.string.no_internet),
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer(alpha = 0.99f)
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradientBrush, blendMode = BlendMode.SrcAtop)
                    }
                },

            )
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.no_internet),
            color = MaterialTheme.colorScheme.error
        )
    }
}
@Preview(showBackground = true)
@Composable
fun NoInternetPreview(){
    NoInternet()
}