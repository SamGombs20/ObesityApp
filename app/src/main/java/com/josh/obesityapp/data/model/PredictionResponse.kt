package com.josh.obesityapp.data.model

import com.google.gson.annotations.SerializedName

data class PredictionResponse(
    @SerializedName("prediction") val prediction: String
)
data class RecommendationResponse(
    @SerializedName("recommendation") val recommendation: String

)