package com.tambo360.app.feature.authentication.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class LoginUserDataResponse(
    @SerializedName("user") val user: UserResponse,
    @SerializedName("token") val token: String
)
