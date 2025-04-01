package com.josh.obesityapp.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.delay

@Composable
fun NoInternet() {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(customDarkGreen, customBrown)
    )

    val scale = remember { Animatable(0.5f) }
    val alpha = remember { Animatable(0f) }
    var startShaking by remember { mutableStateOf(false) }

    val infiniteTransition = rememberInfiniteTransition()
    val shakeOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(100, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    LaunchedEffect(Unit) {
        // Initial animations (scale & fade-in)
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
        )
        alpha.animateTo(0.99f, animationSpec = tween(500))

        while (true) {
            delay(4000) // Wait 4 seconds before shaking
            startShaking = true
            delay(1000) // Shake duration
            startShaking = false
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.no_wifi),
            contentDescription = stringResource(R.string.no_internet),
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer(
                    scaleX = scale.value,
                    scaleY = scale.value,
                    alpha = alpha.value,
                    translationX = if (startShaking) shakeOffset else 0f
                )
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradientBrush, blendMode = BlendMode.SrcAtop)
                    }
                }
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.no_internet),
            color = MaterialTheme.colorScheme.error
        )
        Spacer(Modifier.height(32.dp))
        Text(
            text = stringResource(R.string.network_message)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun NoInternetPreview(){
    NoInternet()
}