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
fun EatingHabitsScreen(navController: NavController, viewModel: UserInputViewModel){
    var caloricQuery by remember { mutableStateOf("") }
    val caloricOptions = listOf("Yes", "No")
    var mealQuery by remember { mutableStateOf("") }
    val mainMeals = listOf("1","2","3", "4")
    var vegetableQuery by remember { mutableStateOf("") }
    val vegetableOptions = listOf("Rarely", "Sometimes", "Always")
    var fastFoodQuery by remember { mutableStateOf("") }
    val fastFoodOptions = listOf("No","Sometimes", "Frequently","Always")
    var caloricError by remember { mutableStateOf<String?>(null) }
    var mealError by remember { mutableStateOf<String?>(null) }
    var vegetableError by remember { mutableStateOf<String?>(null) }
    var fastFoodError by remember { mutableStateOf<String?>(null) }
    fun validateInputs(): Boolean {
        var isValid = true

        if (caloricQuery.isBlank()) {
            caloricError = "Please select an option"
            isValid = false
        } else {
            caloricError = null
        }

        if (mealQuery.isBlank() || mealQuery.toIntOrNull() == null || mealQuery.toInt() !in 1..4) {
            mealError = "Select a valid number of meals (1-4)"
            isValid = false
        } else {
            mealError = null
        }

        if (vegetableQuery.isBlank()) {
            vegetableError = "Please select an option"
            isValid = false
        } else {
            vegetableError = null
        }

        if (fastFoodQuery.isBlank()) {
            fastFoodError = "Please select an option"
            isValid = false
        } else {
            fastFoodError = null
        }

        return isValid
    }

    Column(modifier = Modifier.fillMaxSize().padding(8 .dp),
        verticalArrangement = Arrangement.Center) {
        Icon(
            painter = painterResource(R.drawable.eating),
            contentDescription = stringResource(R.string.eating_habit),
            modifier = Modifier.size(
                100 .dp
            ).align(Alignment.CenterHorizontally),
            tint = customDarkGreen

        )
        CustomTitle(
            strRes = R.string.eating_habit
        )
        LazyColumn {
            item {
                CustomSurface {
                    Column(modifier = Modifier.padding(8 .dp)) {
                        CustomQueryTitle(strRes = R.string.caloric_query)
                        CustomRadioButtonGroup(
                            options = caloricOptions,
                            selectedOption = caloricQuery,
                            onOptionSelected = {
                                caloricQuery = it
                                caloricError = null
                            }
                        )
                        if (caloricError != null){
                            ErrorText(text = caloricError!!)
                        }
                        FillWidthHorizontalDivider()
                        CustomQueryTitle(strRes = R.string.main_meals)
                        CustomRadioButtonGroup(
                            options = mainMeals,
                            selectedOption = mealQuery,
                            onOptionSelected = {
                                mealQuery = it
                                mealError= null},
                            isRow = true)
                        if (mealError != null){
                            ErrorText(text = mealError!!)
                        }
                        FillWidthHorizontalDivider()
                        CustomQueryTitle(strRes = R.string.veg_intake)
                        CustomRadioButtonGroup(
                            options = vegetableOptions,
                            selectedOption = vegetableQuery,
                            onOptionSelected = {
                                vegetableQuery = it
                                vegetableError = null
                            }
                        )
                        if (vegetableError != null){
                            ErrorText(text = vegetableError!!)
                        }
                        FillWidthHorizontalDivider()
                        CustomQueryTitle(strRes = R.string.snack_meals)
                        CustomRadioButtonGroup(
                            options = fastFoodOptions,
                            selectedOption = fastFoodQuery,
                            onOptionSelected = {
                                fastFoodQuery = it
                                fastFoodError = null
                            }
                        )
                        if (fastFoodError != null){
                            ErrorText(text = fastFoodError!!)
                        }
                    }
                }
                Spacer(Modifier.height(32 .dp))
                NavButton(
                    onClick = {
                        if(validateInputs()){
                            viewModel.updateEatingHabits(
                                caloricFoodFrequency = caloricQuery,
                                mainMeals = mealQuery.toInt(),
                                vegetableIntake = vegetableQuery,
                                snackIntake = fastFoodQuery
                            )
                            navController.navigate(NestedNavigationItem.DailyInfo.route){
                                popUpTo(NestedNavigationItem.DailyInfo.route){
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
