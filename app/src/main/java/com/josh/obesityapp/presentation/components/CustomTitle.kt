package com.josh.obesityapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josh.obesityapp.R
import com.josh.obesityapp.ui.theme.customBrown
import com.josh.obesityapp.ui.theme.customSeaGreen

@Composable
fun CustomTitle(strRes:Int, modifier: Modifier = Modifier, underline:Boolean = true){
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(strRes),
            fontSize = 22 .sp,
            textAlign = if (underline) TextAlign.Center else TextAlign.Start,
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = listOf(customSeaGreen, customBrown)
                )
            ),
            fontWeight = FontWeight(500)
        )
        if (underline){
            Spacer(Modifier.height(8 .dp))
            CustomHorizontalDivider()
        }
    }
}
@Composable
fun CustomQueryTitle(strRes: Int){
    Text(
        text = stringResource(strRes),
        fontSize = 18 .sp,
        fontWeight = FontWeight(500)
    )
}
@Composable
fun ErrorText(text:String){
    Text(
        text = text,
        color = MaterialTheme.colorScheme.error,
        modifier = Modifier.padding(vertical = 4 .dp),
        fontSize = 12 .sp
    )
}
@Preview(showBackground = true)
@Composable
fun CustomTitlePreview(){
    CustomTitle(
        strRes = R.string.personal_details
    )
}