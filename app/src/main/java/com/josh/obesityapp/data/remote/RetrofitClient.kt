package com.josh.obesityapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL_OBESITY = "https://obesityml.onrender.com"
    private const val BASE_URL_BLOG = "https://40v865zp.api.sanity.io/"

    private val retrofitObesity: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_OBESITY)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val retrofitBlog: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_BLOG)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val obesityApiService:ObesityApiService by lazy {
        retrofitObesity.create(ObesityApiService::class.java)
    }
    val blogApiService:BlogApiService by lazy {
        retrofitBlog.create(BlogApiService::class.java)
    }
}