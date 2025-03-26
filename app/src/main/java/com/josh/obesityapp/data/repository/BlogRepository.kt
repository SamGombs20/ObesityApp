package com.josh.obesityapp.data.repository


import android.util.Log
import com.josh.obesityapp.data.model.Blog
import com.josh.obesityapp.data.remote.RetrofitClient

class BlogRepository {
    private val blogApiService = RetrofitClient.blogApiService

    suspend fun fetchBlogPosts(): List<Blog>? {
        val response = blogApiService.getBlogs()
        return if (response.isSuccessful) {
            val blogPosts = response.body()
            Log.d("BlogRepository", "Response: ${response.raw()}")

            blogPosts?.results
        } else {
            Log.e("BlogRepository", "Error fetching blog posts: ${response.raw()}")
            null
        }
    }
}