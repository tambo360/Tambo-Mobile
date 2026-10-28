package com.tambo360.app.feature.authentication.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class LoginUserRequest(
    @SerializedName("correo") val correo: String,
    @SerializedName("contraseña") val contrasena: String
)
