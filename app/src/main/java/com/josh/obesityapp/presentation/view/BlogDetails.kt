package com.josh.obesityapp.presentation.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.josh.obesityapp.presentation.viewmodel.BlogViewModel

@Composable
fun BlogDetailsScreen(blogViewModel: BlogViewModel){
    val selectedBlog by blogViewModel.selectedBlog.collectAsState()
    if (selectedBlog != null){
        Text(
            text = selectedBlog!!.title
        )
    }
}