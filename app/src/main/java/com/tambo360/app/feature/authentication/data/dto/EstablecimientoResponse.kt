package com.tambo360.app.feature.authentication.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class EstablecimientoResponse(
    @SerializedName("idEstablecimiento") val idEstablecimiento: String,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("cuestionarioCompletado") val cuestionarioCompletado: Boolean
)
