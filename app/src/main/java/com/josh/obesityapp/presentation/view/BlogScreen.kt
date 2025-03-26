package com.josh.obesityapp.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.josh.obesityapp.presentation.viewmodel.BlogViewModel

@Composable
fun BlogScreen(blogViewModel: BlogViewModel){
    val blogs by blogViewModel.blogs.collectAsState()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(
            text = blogs[0].title
        )
    }
}