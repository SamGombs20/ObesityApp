package com.josh.obesityapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josh.obesityapp.ui.theme.customBrown
import com.josh.obesityapp.ui.theme.customDarkGreen
import com.josh.obesityapp.ui.theme.customLightBrown
import com.josh.obesityapp.ui.theme.customLightGreen
import com.josh.obesityapp.ui.theme.customSeaGreen

@Composable
fun CustomTextField(
    value:String,
    onValueChange:(String)->Unit,
    modifier: Modifier = Modifier,
    label:String? = null,
    keyboardType: KeyboardType = KeyboardType.Text

){
    OutlinedTextField(
        value = value,
        label = {label?.let { Text(it) }},
        modifier = modifier,
        maxLines = 1,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = customBrown,
            unfocusedBorderColor = customDarkGreen,
            focusedLabelColor = customBrown,
            unfocusedLabelColor = customDarkGreen,
            cursorColor = customSeaGreen,
            focusedContainerColor = customLightBrown,
            unfocusedContainerColor = customLightGreen,
            focusedTextColor = customBrown,
            unfocusedTextColor = customDarkGreen
        ),
        shape = RoundedCornerShape(20 .dp)
    )
}
@Composable
fun CustomRadioButtonGroup(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    isRow: Boolean = false
) {
    if (isRow) {
        Row(modifier = Modifier.fillMaxWidth()) {
            options.forEach { option ->
                RadioButtonItem(option, selectedOption, onOptionSelected)
            }
        }
    } else {
        Column(modifier = Modifier.fillMaxWidth()) {
            options.forEach { option ->
                RadioButtonItem(option, selectedOption, onOptionSelected)
            }
        }
    }
}

@Composable
private fun RadioButtonItem(
    option: String,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onOptionSelected(option) }
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(18.dp)
                .border(2.dp, if (selectedOption == option) customDarkGreen else customBrown, CircleShape)
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            if (selectedOption == option) {
                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .background(color = customDarkGreen, shape = CircleShape)
                )
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = option,
            color = if (selectedOption == option) customDarkGreen else customBrown,
            fontWeight = if (selectedOption == option) FontWeight.Medium else FontWeight.Normal
        )
    }
}



@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview(){
    CustomRadioButtonGroup(
        options = listOf("Option 1", "Option 2", "Option 3"),
        selectedOption = "Option 1",
        onOptionSelected = {}
    )
}