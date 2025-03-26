package com.josh.obesityapp.data.repository

import com.josh.obesityapp.data.model.APIInput
import com.josh.obesityapp.data.model.PredictionResponse
import com.josh.obesityapp.data.remote.RetrofitClient

class PredictionRepository {
    private val apiService = RetrofitClient.apiService
    suspend fun getPrediction(apiInput: APIInput):PredictionResponse{
        return apiService.getPrediction(apiInput)
    }
}