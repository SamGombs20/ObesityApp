package com.josh.obesityapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.josh.obesityapp.R

@Composable
fun NoInternet(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Image(
            painter = painterResource(R.drawable.no_wifi),
            contentDescription = stringResource(R.string.no_internet),
            modifier = Modifier.size(
                width = 200.dp,
                height = 200.dp
            )
        )
        Spacer(Modifier.height(16 .dp))
        Text(
            text = stringResource(R.string.no_internet),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}