package com.josh.obesityapp.data.model

data class UserInput(
    var gender: String = "",
    var age: String = "",
    var height: String = "",
    var weight: String = "",
    var familyHistory: String = "",

    var caloricFoodFrequency: String = "",
    var mainMeals: Int = 3,
    var vegetableIntake: String = "",
    var snackIntake: String = "",

    var waterIntake: String = "",
    var calorieMonitoring: String = "",
    var smoking: String = "",
    var alcoholConsumption: String = "",

    var exerciseFrequency: String = "",
    var technologyUsage: String = "",
    var transportMode: String = ""
)
data class SubmittedUserInput(
    val gender: String="",
    val age: Int=1,
    val height: Float=1.0f,
    val weight: Float=1.0f,
    val familyHistory: String="",
    val caloricFoodFrequency: String="",
    val mainMeals: Float=1.0f,
    val vegetableIntake: Float=1.0f,
    val snackIntake: String="",
    val waterIntake: Float= 1.0f,
    val calorieMonitoring: String="",
    val smoking: String="",
    val alcoholConsumption: String="",
    val exerciseFrequency: Float=1.0f,
    val technologyUsage: Float=1.0f,
    val transportMode: String= ""

)
data class APIInput(
    val Gender: String = "",
    val Age: Int = 1,
    val Height: Float = 1.0f,
    val Weight: Float = 1.0f,
    val family_history_with_overweight: String = "",
    val FAVC: String = "",
    val FCVC: Float = 1.0f,
    val NCP: Float = 1.0f,
    val CAEC: String = "",
    val SMOKE: String = "",
    val CH2O: Float = 1.0f,
    val SCC: String = "",
    val FAF: Float = 1.0f,
    val TUE: Float = 1.0f,
    val CALC: String = "",
    val MTRANS: String = ""
)