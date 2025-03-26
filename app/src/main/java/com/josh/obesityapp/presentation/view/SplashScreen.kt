package com.josh.obesityapp.presentation.view

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController
import com.josh.obesityapp.R
import com.josh.obesityapp.navigation.Screen
import com.josh.obesityapp.ui.theme.customBrown
import com.josh.obesityapp.ui.theme.customDarkGreen
import com.josh.obesityapp.ui.theme.customSeaGreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){


    // State for animation scale
    var isScaled by remember { mutableStateOf(false) }

    // Animated scale value
    val scale by animateFloatAsState(
        targetValue = if (isScaled) 1.5f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = ""
    )

    //Fade-in animation
    var isVisible by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        targetValue = if(isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )

    LaunchedEffect (Unit) {
        isScaled = true
        isVisible = true
        delay(3000)
        navController.navigate(Screen.MainScreen.route){
            popUpTo(Screen.SplashScreen.route){
                inclusive = true
            }

        }
    }


    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

        Icon(
            painter = painterResource(R.drawable.app_bar_icon),
            contentDescription = stringResource(R.string.app_name),
            modifier = Modifier.size(100 .dp * scale).alpha(alpha),
            tint = customSeaGreen
        )
        Spacer(Modifier.height(64 .dp))
            Text(
                text = stringResource(R.string.app_name),
                color = customBrown,
                fontSize = 18 .sp,
                fontWeight = FontWeight(500)
            )
            Spacer(Modifier.height(16 .dp))

        Text(
            text = stringResource(R.string.splash_text),
            fontStyle = FontStyle.Italic,
            color = customDarkGreen,
            fontSize = 12 .sp
        )
    }
}