package com.tambo360.app.feature.authentication.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserDataResponse(
    @SerializedName("idUsuario") val idUsuario: String,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("correo") val correo: String,
    @SerializedName("verificado") val verificado: Boolean,
    @SerializedName("fechaCreacion") val fechaCreacion: String
)
