package com.josh.obesityapp.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.josh.obesityapp.data.model.BlogType
import com.josh.obesityapp.data.repository.BlogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BlogViewModel(application: Application):AndroidViewModel(application) {
    private val blogRepository = BlogRepository()
    private val _blogs = MutableStateFlow<List<BlogType>>(emptyList())
    val blogs: MutableStateFlow<List<BlogType>>
        get() = _blogs

    private val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val _isConnected = MutableStateFlow(false)
    val isConnected: MutableStateFlow<Boolean>
        get() = _isConnected


    init {
        observeNetworkConnectivity()
    }
    private fun observeNetworkConnectivity(){
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                _isConnected.value = true
                loadBlogPosts()
            }
            override fun onLost(network: Network) {
                super.onLost(network)
                _isConnected.value = false
            }
        }
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    private fun loadBlogPosts() {
        viewModelScope.launch {
            try {
                val fetchedBlogs = blogRepository.fetchBlogPosts()
                if (fetchedBlogs != null) {
                    _blogs.value = fetchedBlogs
                    Log.d("BlogViewModel", "Fetched blogs: ${_blogs.value[0].author}")

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}