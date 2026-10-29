package com.tambo360.app.feature.authentication.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class VerifyEmailRequest(
    @SerializedName("token") val token: String
)
