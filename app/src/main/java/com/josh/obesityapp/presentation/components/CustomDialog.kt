package com.josh.obesityapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josh.obesityapp.R
import com.josh.obesityapp.ui.theme.customBrown
import com.josh.obesityapp.ui.theme.customDarkGreen

@Composable
fun CustomAlert(
    titleRes:Int,isAbout:Boolean,recommendation:String="",
    messageRes:Int?=null, messageText:String?=null,onDismiss:()->Unit,
    iconRes:Int){
    AlertDialog(
        containerColor = Color.White,
        icon = {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(48 .dp).background(
                    customBrown, CircleShape
                ).padding(8 .dp)
            )
        },
        onDismissRequest = onDismiss,
        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(titleRes),
                    color =  customDarkGreen,
                    fontSize = 18 .sp
                )
                CustomHorizontalDivider()
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (!isAbout){
                    Text(
                        text = stringResource(R.string.result_text)
                    )
                }
                Spacer(Modifier.height(8 .dp))
                Text(
                    text = messageText ?: stringResource(messageRes!!),
                    fontWeight = if (!isAbout) FontWeight(600) else FontWeight.Normal
                )
                if (recommendation.isNotEmpty()){
                    Spacer(Modifier.height(8 .dp))
                    Text(
                        text = stringResource(R.string.recommendation),
                        fontWeight = FontWeight(600)
                    )
                    Text(
                        text = recommendation
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(
                    containerColor = customBrown,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(R.string.close)
                )
            }
        },
    )
}