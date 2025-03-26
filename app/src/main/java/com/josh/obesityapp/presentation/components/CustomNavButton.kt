package com.josh.obesityapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.josh.obesityapp.ui.theme.customBrown

@Composable
fun NavButton(onClick: () -> Unit, modifier: Modifier = Modifier, strRes:Int,
              enabled:Boolean = true){
    TextButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            contentColor = Color.White,
            containerColor = customBrown,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.White
        )
    ) {
        Text(
            text = stringResource(strRes)
        )
    }
}