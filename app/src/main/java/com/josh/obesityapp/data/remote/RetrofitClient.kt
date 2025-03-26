package com.josh.obesityapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL_OBESITY = "https://obesityml.onrender.com"

    val obesityApiService:ObesityApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_OBESITY)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ObesityApiService::class.java)
    }
}