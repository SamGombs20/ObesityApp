package com.josh.obesityapp.navigation

sealed class Screen (val route:String){
    data object SplashScreen:Screen("splash_screen")
    data object MainScreen:Screen("main_screen")

}