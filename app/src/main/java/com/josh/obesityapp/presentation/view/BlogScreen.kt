package com.josh.obesityapp.presentation.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.josh.obesityapp.presentation.components.BlogItem
import com.josh.obesityapp.presentation.components.NoInternet
import com.josh.obesityapp.presentation.viewmodel.BlogViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BlogScreen(navController: NavController,blogViewModel: BlogViewModel){
    val blogs by blogViewModel.blogs.collectAsState()
    val isConnected by blogViewModel.isConnected.collectAsState()
    if (isConnected && blogs.isNotEmpty()){
        Column(
            Modifier.fillMaxSize().background(color = Color.White)
        ) {
            LazyColumn {
                items(blogs.size){
                    BlogItem(blogs[it]){
                        blogViewModel.setSelectedBlog(blogs[it])
                        navController.navigate("blog_details")
                    }
                }
            }
        }
    }
    else{
        NoInternet()
    }
}