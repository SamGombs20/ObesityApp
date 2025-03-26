package com.josh.obesityapp.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.josh.obesityapp.R
import com.josh.obesityapp.navigation.NestedNavigationItem
import com.josh.obesityapp.presentation.components.CustomQueryTitle
import com.josh.obesityapp.presentation.components.CustomRadioButtonGroup
import com.josh.obesityapp.presentation.components.CustomSurface
import com.josh.obesityapp.presentation.components.CustomTitle
import com.josh.obesityapp.presentation.components.ErrorText
import com.josh.obesityapp.presentation.components.FillWidthHorizontalDivider
import com.josh.obesityapp.presentation.components.NavButton
import com.josh.obesityapp.presentation.viewmodel.UserInputViewModel
import com.josh.obesityapp.ui.theme.customDarkGreen

@Composable
fun DailyActivityScreen(navController: NavController,viewModel: UserInputViewModel){
    var waterQuery by remember { mutableStateOf("") }
    val waterOptions = listOf("less than 3 glasses", "3-8 glasses", "more than 8 glasses")
    var caloriesMonitored by remember { mutableStateOf("") }
    val caloriesOptions = listOf("Yes", "No")
    var smokeQuery by remember { mutableStateOf("") }
    val smokeOptions = listOf("Yes", "No")
    var alcoholQuery by remember { mutableStateOf("") }
    val alcoholOptions = listOf("I don't","Sometimes", "Frequently", "Always")
    var waterError by remember { mutableStateOf<String?>(null) }
    var caloriesError by remember { mutableStateOf<String?>(null) }
    var smokeError by remember { mutableStateOf<String?>(null) }
    var alcoholError by remember { mutableStateOf<String?>(null) }

    fun validateInputs(): Boolean {
        var isValid = true

        if (waterQuery.isBlank()) {
            waterError = "Please select an option"
            isValid = false
        } else {
            waterError = null
        }

        if (caloriesMonitored.isBlank()) {
            caloriesError = "Please select an option"
            isValid = false
        } else {
            caloriesError = null
        }

        if (smokeQuery.isBlank()) {
            smokeError = "Please select an option"
            isValid = false
        } else {
            smokeError = null
        }

        if (alcoholQuery.isBlank()) {
            alcoholError = "Please select an option"
            isValid = false
        } else {
            alcoholError = null
        }

        return isValid
    }



    Column(modifier = Modifier.fillMaxSize().padding(8 .dp),
        verticalArrangement = Arrangement.Center) {

        Icon(
            painter = painterResource(R.drawable.daily),
            contentDescription = stringResource(R.string.lifestyle),
            tint = customDarkGreen,
            modifier = Modifier
                .size(100 .dp)
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16 .dp)
        )
        CustomTitle(strRes = R.string.lifestyle)
        LazyColumn {
            item {
                CustomSurface {
                    Column(modifier = Modifier.padding(8 .dp)) {
                        CustomQueryTitle(strRes = R.string.water)
                        CustomRadioButtonGroup(
                            selectedOption = waterQuery,
                            options = waterOptions,
                            onOptionSelected = {waterQuery = it
                                waterError = null}
                        )
                        if (waterError != null){
                            ErrorText(text = waterError!!)
                        }
                        FillWidthHorizontalDivider()
                        CustomQueryTitle(strRes = R.string.monitor)
                        CustomRadioButtonGroup(
                            selectedOption = caloriesMonitored,
                            options = caloriesOptions,
                            onOptionSelected = {caloriesMonitored = it
                                caloriesError = null}
                        )
                        if (caloriesError != null){
                            ErrorText(text = caloriesError!!)
                        }
                        FillWidthHorizontalDivider()
                        CustomQueryTitle(strRes = R.string.smoke)
                        CustomRadioButtonGroup(
                            selectedOption = smokeQuery,
                            options = smokeOptions,
                            onOptionSelected = {smokeQuery = it
                                smokeError = null}
                        )
                        if (smokeError != null){
                            ErrorText(text = smokeError!!)
                        }
                        FillWidthHorizontalDivider()
                        CustomQueryTitle(strRes = R.string.alcohol)
                        CustomRadioButtonGroup(
                            selectedOption = alcoholQuery,
                            options = alcoholOptions,
                            onOptionSelected = {alcoholQuery = it
                                alcoholError = null})
                        if (alcoholError != null){
                            ErrorText(text = alcoholError!!)
                        }
                    }


                }
                Spacer(Modifier.height(32 .dp))
                NavButton(
                    onClick = {
                        if(validateInputs()){
                            viewModel.updateDailyActivity(
                                waterIntake = waterQuery,
                                calorieMonitoring = caloriesMonitored,
                                smoking = smokeQuery,
                                alcoholConsumption = alcoholQuery
                            )
                            navController.navigate(NestedNavigationItem.TransportInfo.route){
                                popUpTo(NestedNavigationItem.TransportInfo.route){
                                    inclusive = true
                                }
                            }
                        }
                    },
                    strRes = R.string.next_btn
                )
            }
        }

    }

}