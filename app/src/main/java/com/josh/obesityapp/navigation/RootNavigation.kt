package com.josh.obesityapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josh.obesityapp.presentation.view.MainScreen
import com.josh.obesityapp.presentation.view.SplashScreen

@Composable
fun RootNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ){
        composable(Screen.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(Screen.MainScreen.route){
            MainScreen()
        }

    }
}