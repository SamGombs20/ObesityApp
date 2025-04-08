package com.josh.obesityapp.data.remote

import com.josh.obesityapp.data.model.APIInput
import com.josh.obesityapp.data.model.PredictionResponse
import com.josh.obesityapp.data.model.RecommendationResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ObesityApiService{
    @POST("/predict")
    suspend fun getPrediction(@Body apiInput: APIInput):PredictionResponse
    @GET("/recommendation")
    suspend fun getRecommendation(@Body predictionResponse: PredictionResponse):RecommendationResponse
}