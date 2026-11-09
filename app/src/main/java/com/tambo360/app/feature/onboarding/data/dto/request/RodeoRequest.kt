package com.tambo360.app.feature.onboarding.data.dto.request

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RodeoRequest(
    @SerializedName("tipoRodeo") val tipoRodeo: String,
    @SerializedName("costoRacion") val costoRacion: Double,
    @SerializedName("razas") val razas: List<RazaRequest>
)
