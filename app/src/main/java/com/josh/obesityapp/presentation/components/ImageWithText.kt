package com.josh.obesityapp.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseOutBounce
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josh.obesityapp.R
import com.josh.obesityapp.ui.theme.customBrown
import com.josh.obesityapp.ui.theme.customDarkGreen
import com.josh.obesityapp.ui.theme.customSeaGreen

@Composable
fun ColumnImageText(imgRes:Int, strRes:Int, topStrRes:Int? = null, modifier: Modifier = Modifier){

    val scale = remember { Animatable(0f) }
    val alpha = remember { Animatable(0f) }
    LaunchedEffect (Unit) {
        scale.animateTo(1f, animationSpec = tween(1000, easing = EaseOutBounce))
        alpha.animateTo(1f, animationSpec = tween(1000))
    }


    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

        Icon(
            painter = painterResource(imgRes),
            contentDescription = stringResource(strRes),
            modifier = Modifier.size(150 .dp),
            tint = customSeaGreen
        )
        Spacer(Modifier.height(64 .dp))
        if(topStrRes != null){
            Text(
                text = stringResource(topStrRes),
                color = customBrown,
                fontSize = 18 .sp,
                fontWeight = FontWeight(500)
                )
            Spacer(Modifier.height(16 .dp))
        }
        Text(
            text = stringResource(strRes),
            fontStyle = FontStyle.Italic,
            color = customDarkGreen,
            fontSize = 12 .sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ColumnImageTextPreview() {
    ColumnImageText(
        imgRes = R.drawable.obese_splash,
        strRes = R.string.splash_text,
        topStrRes = R.string.app_name,
        modifier = Modifier.fillMaxSize()
    )
}