package com.tambo360.app.feature.onboarding.data.dto.request

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AnimalRequest(
    @SerializedName("codigo") val codigo: String,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("categoria") val categoria: String,
    @SerializedName("estado") val estado: String,
    @SerializedName("fechaNacimiento") val fechaNacimiento: String? = null,
    @SerializedName("raza") val raza: String
)
