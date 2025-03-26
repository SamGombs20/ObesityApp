package com.josh.obesityapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.josh.obesityapp.navigation.RootNavigation
import com.josh.obesityapp.ui.theme.ObesityAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ObesityAppTheme {
                RootNavigation()
            }
        }
    }
}