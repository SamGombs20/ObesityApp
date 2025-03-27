package com.josh.obesityapp.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.josh.obesityapp.R
import com.josh.obesityapp.navigation.NestedNavigationItem
import com.josh.obesityapp.ui.theme.customBrown
import com.josh.obesityapp.ui.theme.customLightGreen

@Composable
fun HomeScreen(navController: NavController){
    LazyColumn (modifier = Modifier.padding(8 .dp)){
        item {
            Surface(
                modifier = Modifier.fillMaxWidth().height(150 .dp),
                color = customLightGreen,
                shape = RoundedCornerShape(20 .dp)
            ) {
                Row( verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8 .dp)) {
                    Column(modifier = Modifier.weight(0.6f).padding(start = 16 .dp)) {
                        Text(
                            text = stringResource(R.string.intro_text)
                        )
                        Spacer(Modifier.height(16 .dp))
                        TextButton(
                            onClick = {
                                navController.navigate(NestedNavigationItem.PersonalInfo.route)
                            },
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = customBrown,
                                contentColor = Color.White
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(R.string.intro_btn_txt),
                            )
                        }
                    }
                    Image(
                        painter = painterResource(R.drawable.obese_intro),
                        contentDescription = stringResource(R.string.intro_text),
                        modifier = Modifier.fillMaxHeight().weight(0.38f),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Spacer(Modifier.height(16 .dp))
        }
        item {
            Text(
                text = stringResource(R.string.how_title),
                fontSize = 22 .sp,
                fontWeight = FontWeight(500)
            )
            Spacer(Modifier.height(8 .dp))
        }
        item {
            Surface(
                modifier = Modifier.fillMaxWidth().height(300 .dp).border(
                    width = 1.dp,
                    color = customLightGreen,
                    shape = RoundedCornerShape(10 .dp)
                ),
                color = Color.White,
                shape = RoundedCornerShape(10 .dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(8 .dp)) {
                    Image(
                        painter = painterResource(R.drawable.how),
                        contentDescription = stringResource(R.string.how_text),
                        modifier = Modifier.weight(0.7f),
                        contentScale = ContentScale.Crop
                    )
                    Column(Modifier.padding(horizontal = 8 .dp)) {
                        Text(
                            text = stringResource(R.string.how_text)
                        )
                        TextButton(
                            onClick = {
                                navController.navigate(NestedNavigationItem.WorkingInfo.route){
                                    popUpTo(NestedNavigationItem.WorkingInfo.route){
                                        inclusive = true
                                    }
                                }
                            },
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = customBrown
                            )
                        ) {
                            Text(
                                text = stringResource(R.string.read_on),

                                )
                        }
                    }
                }
            }
        }
    }
}
