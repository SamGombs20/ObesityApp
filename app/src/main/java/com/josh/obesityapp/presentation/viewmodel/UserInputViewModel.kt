package com.josh.obesityapp.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josh.obesityapp.data.model.APIInput
import com.josh.obesityapp.data.model.SubmittedUserInput
import com.josh.obesityapp.data.model.UserInput
import com.josh.obesityapp.data.repository.PredictionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserInputViewModel(application: Application):AndroidViewModel(application) {
    private val repository = PredictionRepository()
    private val _userInput = mutableStateOf(UserInput())
    private val _rawInput = mutableStateOf(SubmittedUserInput())
    private val _apiInput = mutableStateOf(APIInput())
    private val apiInput: State<APIInput> = _apiInput
    private val _predictionResult = MutableStateFlow("")
    val predictionResult: StateFlow<String> =  _predictionResult
    private val _recommendationResult = MutableStateFlow("")
    val recommendationResult: StateFlow<String> =  _recommendationResult

    private val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val _isConnected = mutableStateOf(false)
    val isConnected: State<Boolean> = _isConnected

    init {
        observeNetworkConnectivity()
    }
    private fun observeNetworkConnectivity(){
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                _isConnected.value = true
            }
            override fun onLost(network: Network) {
                super.onLost(network)
                _isConnected.value = false
            }
        }
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    fun updatePersonalDetails(gender:String,familyHistory:String,age:String,height:String,weight:String){
        _userInput.value = _userInput.value.copy(
            gender = gender,
            familyHistory = familyHistory,
            age = age,
            height = height,
            weight = weight
        )
    }
    fun updateEatingHabits(caloricFoodFrequency:String,mainMeals:Int,vegetableIntake:String,snackIntake:String){
        _userInput.value = _userInput.value.copy(
            caloricFoodFrequency = caloricFoodFrequency,
            mainMeals = mainMeals,
            vegetableIntake = vegetableIntake,
            snackIntake = snackIntake)
    }
    fun updateDailyActivity(waterIntake:String,calorieMonitoring:String,smoking:String,alcoholConsumption:String){
        _userInput.value = _userInput.value.copy(
            waterIntake = waterIntake,
            calorieMonitoring = calorieMonitoring,
            smoking = smoking,
            alcoholConsumption = alcoholConsumption
        )
    }
    fun updateTransport(exerciseFrequency:String,technologyUsage:String,transportMode:String){
        _userInput.value = _userInput.value.copy(
            exerciseFrequency = exerciseFrequency,
            technologyUsage = technologyUsage,
            transportMode = transportMode
        )
    }
    private fun convertUserInputToRawInput(){
        _rawInput.value = SubmittedUserInput(
            gender = _userInput.value.gender,
            age = _userInput.value.age.toInt(),
            height = _userInput.value.height.toFloat(),
            weight = _userInput.value.weight.toFloat(),
            familyHistory = _userInput.value.familyHistory.lowercase(),
            caloricFoodFrequency = _userInput.value.caloricFoodFrequency.lowercase(),
            mainMeals = _userInput.value.mainMeals.toFloat(),
            vegetableIntake = extractVegData(_userInput.value.vegetableIntake),
            snackIntake = extractSnackData(_userInput.value.snackIntake),
            waterIntake = extractWaterData(_userInput.value.waterIntake),
            calorieMonitoring = _userInput.value.calorieMonitoring.lowercase(),
            smoking = _userInput.value.smoking.lowercase(),
            alcoholConsumption = extractAlcoholData(_userInput.value.alcoholConsumption),
            exerciseFrequency = extractExerciseData(_userInput.value.exerciseFrequency),
            technologyUsage = extractTechnologyData(_userInput.value.technologyUsage),
            transportMode = extractTransportData(_userInput.value.transportMode)

        )
    }
    private fun convertRawInputToAPIInput(){
        _apiInput.value = APIInput(
            Gender = _rawInput.value.gender,
            Age = _rawInput.value.age,
            Height = _rawInput.value.height,
            Weight = _rawInput.value.weight,
            family_history_with_overweight = _rawInput.value.familyHistory,
            FAVC = _rawInput.value.caloricFoodFrequency,
            FCVC = _rawInput.value.vegetableIntake,
            NCP = _rawInput.value.mainMeals,
            CAEC = _rawInput.value.snackIntake,
            SMOKE = _rawInput.value.smoking,
            CH2O = _rawInput.value.waterIntake,
            SCC = _rawInput.value.calorieMonitoring,
            FAF = _rawInput.value.exerciseFrequency,
            TUE = _rawInput.value.technologyUsage,
            CALC = _rawInput.value.alcoholConsumption,
            MTRANS = _rawInput.value.transportMode
        )
    }
    private fun extractVegData(string: String):Float{
        return when(string){
            "Rarely" -> 1.0f
            "Sometimes" -> 2.0f
            "Always" -> 3.0f
            else -> 1.0f}
    }
    private fun extractSnackData(string: String):String{
        return when(string){
            "No" -> "no"
            "Sometimes" -> "Sometimes"
            "Frequently" -> "Frequently"
            "Always" -> "Always"
            else -> "no"}
    }
    private fun extractWaterData(string: String):Float{
        return when(string){
            "less than 3 glasses" -> 1.0f
            "3-8 glasses" -> 2.0f
            "more than 8 glasses" -> 3.0f
            else -> 1.0f}
    }
    private fun extractAlcoholData(string: String):String{
        return when(string){
            "I don't" -> "no"
            "Sometimes" -> "Sometimes"
            "Frequently" -> "Frequently"
            "Always" -> "Always"
            else -> "no"
        }
    }
    private fun extractExerciseData(string: String):Float{
        return when(string){
            "I don't" -> 0.0f
            "Sometimes" -> 1.0f
            "Frequently" -> 2.0f
            "Always" -> 3.0f
            else -> 1.0f}
    }
    private fun extractTechnologyData(string: String):Float{
        return when(string){
            "less than 3 hours" -> 0.0f
            "3-8 hours" -> 1.0f
            "more than 8 hours" -> 2.0f
            else -> 1.0f}
    }
    private fun extractTransportData(string: String):String{
        return when(string){
            "Bike" -> "Bike"
            "Walking" -> "Walking"
            "Motorbike" -> "Motorbike"
            "Automobile" -> "Automobile"
            "Public Transportation" -> "Public_Transportation"
            else -> "Bike"
        }
    }
    private fun getPrediction(){
        viewModelScope.launch {
            try {

                val response = repository.getPrediction(apiInput.value)
                _predictionResult.value = response.prediction
                Log.d("Prediction", predictionResult.value)
            }catch (e:Exception){
                Log.e("PredictionError",e.printStackTrace().toString())
            }
        }
    }
    fun submitData(){
        if (_isConnected.value){
            convertUserInputToRawInput()
            convertRawInputToAPIInput()
            getPrediction()
            Log.d("APIInput",apiInput.value.toString())
        }
        else{
            Log.e("NetworkError","No internet connection")
        }


    }

}