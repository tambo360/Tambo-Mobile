package com.tambo360.app.feature.authentication.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ForgotPasswordRequest(
    @SerializedName("correo") val correo: String
)
