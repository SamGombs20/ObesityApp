package com.josh.obesityapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josh.obesityapp.data.model.Blog
import com.josh.obesityapp.data.repository.BlogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BlogViewModel:ViewModel() {
    private val blogRepository = BlogRepository()
    private val _blogs = MutableStateFlow<List<Blog>>(emptyList())
    val blogs: MutableStateFlow<List<Blog>>
        get() = _blogs

    init {
        loadBlogPosts()
    }

    private fun loadBlogPosts() {
        viewModelScope.launch {
            try {
                val fetchedBlogs = blogRepository.fetchBlogPosts()
                if (fetchedBlogs != null) {
                    _blogs.value = fetchedBlogs
                }
                Log.d("BlogViewModel", "Fetched blogs: ${_blogs.value}")
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}