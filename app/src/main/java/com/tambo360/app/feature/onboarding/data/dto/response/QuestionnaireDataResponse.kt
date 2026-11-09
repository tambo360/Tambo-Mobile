package com.tambo360.app.feature.onboarding.data.dto.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionnaireDataResponse(
    @SerializedName("status") val status: String
)
