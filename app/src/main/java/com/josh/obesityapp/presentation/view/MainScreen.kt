package com.josh.obesityapp.presentation.view


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.josh.obesityapp.R
import com.josh.obesityapp.navigation.NavigationItem
import com.josh.obesityapp.navigation.NestedNavigationItem
import com.josh.obesityapp.presentation.components.BottomNavbar
import com.josh.obesityapp.presentation.components.CustomAlert
import com.josh.obesityapp.presentation.viewmodel.BlogViewModel
import com.josh.obesityapp.presentation.viewmodel.UserInputViewModel
import com.josh.obesityapp.ui.theme.customBrown
import com.josh.obesityapp.ui.theme.customDarkGreen

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val userInputViewModel:UserInputViewModel = viewModel()
    val blogViewModel:BlogViewModel = viewModel()
    var showAlert by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(painter = painterResource(R.drawable.app_bar_icon),
                            contentDescription = stringResource(R.string.app_name),
                            tint = customDarkGreen,
                            modifier = Modifier.size(40 .dp)
                                .padding(end = 16 .dp)
                        )
                        Row (verticalAlignment = Alignment.Bottom){
                            Text(
                                text = stringResource(R.string.half_start),
                                color = customBrown,
                                fontSize = 14 .sp
                            )
                            Text(
                                text = stringResource(R.string.half_end),
                                color = customDarkGreen,
                            )
                        }

                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            showAlert = true
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.about),
                            contentDescription = stringResource(R.string.about),
                            tint = customDarkGreen,
                            modifier = Modifier.size(24 .dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .padding(bottom = 1 .dp)
                    .border(
                    width = 1 .dp,
                    color = customBrown,
                    shape = RoundedCornerShape(8 .dp)
                )
            )
        },
        bottomBar = {
            BottomNavbar(navController = navController)
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController = navController,
                startDestination = NavigationItem.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                navigation(
                    startDestination = NestedNavigationItem.Home.route,
                    route = NavigationItem.Home.route
                ){
                    composable(NestedNavigationItem.Home.route){
                        HomeScreen(navController)
                    }
                    composable(NestedNavigationItem.WorkingInfo.route) {
                        HowItWorksScreen(navController)
                    }
                    composable(NestedNavigationItem.PersonalInfo.route){
                        PersonalDetailsScreen(navController, userInputViewModel)
                    }
                    composable(NestedNavigationItem.EatingInfo.route){
                        EatingHabitsScreen(navController, userInputViewModel)
                    }
                    composable(NestedNavigationItem.DailyInfo.route){
                        DailyActivityScreen(navController, userInputViewModel)
                    }
                    composable(NestedNavigationItem.TransportInfo.route){
                        TransportScreen(navController, userInputViewModel)
                    }

                }
                composable(NavigationItem.Blog.route){
                    BlogScreen(blogViewModel)
                }
            }
            if(showAlert){
                CustomAlert(
                    titleRes = R.string.about,
                    isAbout = true,
                    messageRes = R.string.about_info,
                    onDismiss = {showAlert = false},
                    iconRes = R.drawable.about
                )
            }
        }
    }

}