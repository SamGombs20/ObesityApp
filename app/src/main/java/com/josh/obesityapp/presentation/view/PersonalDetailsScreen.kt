package com.josh.obesityapp.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.josh.obesityapp.R
import com.josh.obesityapp.navigation.NestedNavigationItem
import com.josh.obesityapp.presentation.components.CustomQueryTitle
import com.josh.obesityapp.presentation.components.CustomRadioButtonGroup
import com.josh.obesityapp.presentation.components.CustomSurface
import com.josh.obesityapp.presentation.components.CustomTextField
import com.josh.obesityapp.presentation.components.CustomUnderlineTitle
import com.josh.obesityapp.presentation.components.ErrorText
import com.josh.obesityapp.presentation.components.FillWidthHorizontalDivider
import com.josh.obesityapp.presentation.components.NavButton
import com.josh.obesityapp.presentation.viewmodel.UserInputViewModel
import com.josh.obesityapp.ui.theme.customDarkGreen


@Composable
fun PersonalDetailsScreen(navController: NavController, viewModel: UserInputViewModel){
    var selectedGender by remember { mutableStateOf("") }
    var selectedAge by remember { mutableStateOf("") }
    var selectedHeight by remember { mutableStateOf("") }
    var selectedWeight by remember { mutableStateOf("") }
    val genderOptions = listOf("Male", "Female")
    var familyHistoryQuery by remember { mutableStateOf("") }
    val familyHistoryOptions = listOf("Yes", "No")
    var genderError by remember { mutableStateOf<String?>(null) }
    var ageError by remember { mutableStateOf<String?>(null) }
    var heightError by remember { mutableStateOf<String?>(null) }
    var weightError by remember { mutableStateOf<String?>(null) }
    var familyHistoryError by remember { mutableStateOf<String?>(null) }
    fun validateInputs(): Boolean {
        var isValid = true

        if (selectedGender.isBlank()) {
            genderError = "Please select a gender"
            isValid = false
        } else {
            genderError = null
        }

        if (familyHistoryQuery.isBlank()) {
            familyHistoryError = "Please select an option"
            isValid = false
        } else {
            familyHistoryError = null
        }

        if (selectedAge.isBlank() || selectedAge.toIntOrNull() == null || selectedAge.toInt() !in 1..120) {
            ageError = "Enter a valid age (1-120)"
            isValid = false
        } else {
            ageError = null
        }

        if (selectedHeight.isBlank() || selectedHeight.toFloatOrNull() == null || selectedHeight.toFloat() <= 0) {
            heightError = "Enter a valid height"
            isValid = false
        } else {
            heightError = null
        }

        if (selectedWeight.isBlank() || selectedWeight.toFloatOrNull() == null || selectedWeight.toFloat() <= 0) {
            weightError = "Enter a valid weight"
            isValid = false
        } else {
            weightError = null
        }

        return isValid
    }

    Column(modifier = Modifier.fillMaxSize().padding(4 .dp)) {
        Icon(
            painter = painterResource(R.drawable.personal),
            contentDescription = stringResource(R.string.personal_details),
            tint = customDarkGreen,
            modifier = Modifier.size(
                100 .dp
            ).align(Alignment.CenterHorizontally)
        )
        CustomUnderlineTitle(
            strRes = R.string.personal_details
        )
        Spacer(Modifier.height(16 .dp))
        LazyColumn {
            item {
                CustomSurface {
                    Box(modifier = Modifier.padding(12 .dp)){
                        Column {
                            Text(
                                text = stringResource(R.string.gender_label),
                                modifier = Modifier.padding(bottom = 8 .dp),
                                fontSize = 18 .sp,
                                fontWeight = FontWeight(500)
                            )
                            CustomRadioButtonGroup(
                                options = genderOptions,
                                selectedOption = selectedGender,
                                onOptionSelected = {
                                    selectedGender = it
                                    genderError = null
                                }
                            )
                            if (genderError != null){
                                ErrorText(text = genderError!!)
                            }
                            FillWidthHorizontalDivider()
                            CustomQueryTitle(strRes = R.string.family_history)
                            CustomRadioButtonGroup(
                                options = familyHistoryOptions,
                                selectedOption = familyHistoryQuery,
                                onOptionSelected = {
                                    familyHistoryQuery = it
                                    familyHistoryError = null
                                }
                            )
                            if (familyHistoryError != null){
                                ErrorText(text = familyHistoryError!!)
                            }
                            FillWidthHorizontalDivider()
                            CustomTextField(
                                value = selectedAge,
                                onValueChange = {
                                    selectedAge = it
                                    ageError = null
                                },
                                label = stringResource(R.string.age_label),
                                keyboardType = KeyboardType.Number)
                            if (ageError != null){
                                ErrorText(text = ageError!!)

                            }
                            CustomTextField(
                                value = selectedHeight,
                                onValueChange = {
                                    selectedHeight = it
                                    heightError = null
                                },
                                label = stringResource(R.string.height_label),
                                keyboardType = KeyboardType.Number
                            )
                            if (heightError != null) {
                                ErrorText(text = heightError!!)
                            }
                            CustomTextField(
                                value = selectedWeight,
                                onValueChange = {
                                    selectedWeight = it
                                    weightError = null
                                },
                                label = stringResource(R.string.weight_label),
                                keyboardType = KeyboardType.Number
                            )
                            if (weightError != null) {
                                ErrorText(text = weightError!!)
                            }
                        }
                    }
                }
                Spacer(Modifier.height(32 .dp))
                NavButton(
                    onClick = {
                        if (validateInputs()) {
                            viewModel.updatePersonalDetails(
                                gender = selectedGender,
                                familyHistory = familyHistoryQuery,
                                age = selectedAge,
                                height = selectedHeight,
                                weight = selectedWeight
                            )
                            navController.navigate(NestedNavigationItem.EatingInfo.route){
                                popUpTo(NestedNavigationItem.EatingInfo.route){
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
