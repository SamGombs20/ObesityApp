package com.josh.obesityapp.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.josh.obesityapp.R
import com.josh.obesityapp.navigation.NavigationItem
import com.josh.obesityapp.presentation.components.CustomAlert
import com.josh.obesityapp.presentation.components.CustomQueryTitle
import com.josh.obesityapp.presentation.components.CustomRadioButtonGroup
import com.josh.obesityapp.presentation.components.CustomSurface
import com.josh.obesityapp.presentation.components.CustomUnderlineTitle
import com.josh.obesityapp.presentation.components.ErrorText
import com.josh.obesityapp.presentation.components.FillWidthHorizontalDivider
import com.josh.obesityapp.presentation.components.NavButton
import com.josh.obesityapp.presentation.viewmodel.UserInputViewModel
import com.josh.obesityapp.ui.theme.customDarkGreen

@Composable
fun TransportScreen(navController: NavController, viewModel: UserInputViewModel){
    var exerciseQuery by remember { mutableStateOf("") }
    val exerciseOptions = listOf("I don't","Sometimes", "Frequently", "Always")
    var technologyQuery by remember { mutableStateOf("") }
    val technologyOptions = listOf("less than 3 hours", "3-8 hours", "more than 8 hours")
    var transportQuery by remember { mutableStateOf("") }
    val transportOptions = listOf("Bike", "Walking","Motorbike", "Automobile", "Public Transportation")

    var exerciseError by remember { mutableStateOf<String?>(null) }
    var technologyError by remember { mutableStateOf<String?>(null) }
    var transportError by remember { mutableStateOf<String?>(null) }

    val isConnected by viewModel.isConnected
    val prediction by viewModel.predictionResult.collectAsState()
    val recommendation by viewModel.recommendationResult.collectAsState()
    var showAlert by remember { mutableStateOf(false) }

    fun validateInputs(): Boolean {
        var isValid = true

        if (exerciseQuery.isBlank()) {
            exerciseError = "Please select an option"
            isValid = false
        } else {
            exerciseError = null
        }

        if (technologyQuery.isBlank()) {
            technologyError = "Please select an option"
            isValid = false
        } else {
            technologyError = null
        }

        if (transportQuery.isBlank()) {
            transportError = "Please select an option"
            isValid = false
        } else {
            transportError = null
        }

        return isValid
    }
    LaunchedEffect(prediction) {
        if (prediction.isNotEmpty()){
            showAlert =true
        }
    }

    Column(modifier = Modifier.padding(8 .dp).fillMaxSize()) {
        Icon(
            painter = painterResource(R.drawable.physical),
            contentDescription = stringResource(R.string.lifestyle),
            tint = customDarkGreen,
            modifier = Modifier
                .size(100 .dp)
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16 .dp)
        )
        CustomUnderlineTitle(strRes = R.string.lifestyle)
        LazyColumn {
            item {
                CustomSurface {
                    Column(modifier = Modifier.padding(8 .dp)) {
                        CustomQueryTitle(strRes = R.string.physical)
                        CustomRadioButtonGroup(
                            options = exerciseOptions,
                            selectedOption = exerciseQuery,
                            onOptionSelected = {
                                exerciseQuery = it
                                exerciseError = null
                            }
                        )
                        if (exerciseError != null){
                            ErrorText(exerciseError!!)
                        }
                        FillWidthHorizontalDivider()
                        CustomQueryTitle(strRes = R.string.technology)
                        CustomRadioButtonGroup(
                            options = technologyOptions,
                            selectedOption = technologyQuery,
                            onOptionSelected = {technologyQuery = it
                                technologyError = null}
                        )
                        if (technologyError!=null){
                            ErrorText(technologyError!!)
                        }
                        FillWidthHorizontalDivider()
                        CustomQueryTitle(strRes = R.string.transport_means)
                        CustomRadioButtonGroup(
                            options = transportOptions,
                            selectedOption = transportQuery,
                            onOptionSelected = {transportQuery = it
                                transportError = null}
                        )
                        if (transportError!=null){
                            ErrorText(transportError!!)
                        }
                    }
                }
                Spacer(Modifier.height(8 .dp))
                if (!isConnected){
                    Text(
                        text = stringResource(R.string.network_message),
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 14 .sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                Spacer(Modifier.height(32 .dp))
                NavButton(
                    onClick = {
                        if(validateInputs()){
                            viewModel.updateTransport(
                                exerciseFrequency = exerciseQuery,
                                technologyUsage = technologyQuery,
                                transportMode = transportQuery
                            )
                            viewModel.submitData()


                        }
                    },
                    strRes = R.string.submit_btn,
                    enabled = isConnected
                )
            }
            item {
                if (showAlert && isConnected){
                    CustomAlert(
                        isAbout = false,
                        titleRes = R.string.result_title,
                        messageText = prediction,
                        recommendation = recommendation,
                        onDismiss = {
                            navController.navigate(NavigationItem.Home.route){
                                popUpTo(NavigationItem.Home.route){
                                    inclusive = true
                                }
                            }
                        },
                        iconRes = R.drawable.result,
                    )
                }
            }

        }


    }

}

