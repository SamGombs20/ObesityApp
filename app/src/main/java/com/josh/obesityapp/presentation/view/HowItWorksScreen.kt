package com.josh.obesityapp.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.josh.obesityapp.R
import com.josh.obesityapp.navigation.NestedNavigationItem
import com.josh.obesityapp.presentation.components.CustomUnderlineTitle
import com.josh.obesityapp.ui.theme.customBrown


@Composable
fun HowItWorksScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(vertical = 4 .dp, horizontal = 16 .dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        // Title
        CustomUnderlineTitle(
            strRes = R.string.how_title,
            modifier = Modifier.padding(bottom = 8 .dp)
        )

        // Introduction
        Text(
            text = "Our model predicts a person's likelihood of being overweight or obese based on various lifestyle habits, physical characteristics, and daily routines.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Section Titles and Content
        SectionTitle("1. Personal & Physical Information")
        BulletPoint("• Gender & Age: Influence metabolism and body composition.")
        BulletPoint("• Height & Weight: Used to calculate BMI, a key weight indicator.")
        BulletPoint("• Family History: Genetics may contribute to obesity risks.")

        SectionTitle("2. Eating Habits")
        BulletPoint("• Caloric Food Frequency: How often high-calorie foods are consumed.")
        BulletPoint("• Main Meals Per Day: The number of meals eaten daily.")
        BulletPoint("• Vegetable Intake: A balanced diet supports weight management.")
        BulletPoint("• Snack Intake: Frequent unhealthy snacks may lead to weight gain.")

        SectionTitle("3. Hydration & Dietary Awareness")
        BulletPoint("• Water Intake: Drinking water regulates metabolism.")
        BulletPoint("• Calorie Monitoring: Tracking food intake helps manage weight.")

        SectionTitle("4. Lifestyle & Behavioral Factors")
        BulletPoint("• Smoking & Alcohol Consumption: These can affect metabolism.")
        BulletPoint("• Exercise Frequency: Physical activity plays a major role in weight control.")
        BulletPoint("• Technology Usage: Excessive screen time leads to a sedentary lifestyle.")
        BulletPoint("• Mode of Transport: Walking vs. using a car affects daily activity levels.")

        SectionTitle("How the Model Works")
        Text(
            text = "Once you provide this information, our AI-powered model analyzes patterns and predicts your obesity risk based on real-world data and scientific research.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        Text(
            text = "📌 Note: Your data is used solely for prediction and is not stored or shared.",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        TextButton(
            onClick ={
                navController.navigate(NestedNavigationItem.PersonalInfo.route){
                    popUpTo(NestedNavigationItem.PersonalInfo.route){
                        inclusive = true
                    }
                }
            },
            colors = ButtonDefaults.textButtonColors(
                containerColor = customBrown,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.intro_btn_txt)
            )
        }

    }
}

// Helper Composable for Formatting
@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 16 .sp,
        fontWeight = FontWeight(600),
        modifier = Modifier.padding(top = 12.dp, bottom = 6.dp)
    )
}

@Composable
fun BulletPoint(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Justify,
        modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
    )
}

@Composable
@Preview(showBackground = true)
fun HowPreview(){
    HowItWorksScreen(navController = NavController(context = LocalContext.current))
}
