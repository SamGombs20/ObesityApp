package com.josh.obesityapp.data.repository


import android.util.Log
import com.josh.obesityapp.data.model.BlogType
import com.josh.obesityapp.data.remote.RetrofitClient

class BlogRepository {
    private val blogApiService = RetrofitClient.blogApiService

    suspend fun fetchBlogPosts(): List<BlogType>? {
        val response = blogApiService.getBlogs()
        return if (response.isSuccessful) {
            val blogPosts = response.body()
            Log.d("BlogRepository", "Response: ${response.body()}")

            blogPosts?.result
        } else {
            Log.e("BlogRepository", "Error fetching blog posts: ${response.raw()}")
            null
        }
    }
}