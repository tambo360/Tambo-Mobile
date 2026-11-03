package com.tambo360.app.feature.authentication.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String
)
