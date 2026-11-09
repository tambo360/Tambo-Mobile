package com.tambo360.app.feature.onboarding.data.dto.request

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RazaRequest(
    @SerializedName("raza") val raza: String,
    @SerializedName("cantVacas") val cantVacas: Int
)
