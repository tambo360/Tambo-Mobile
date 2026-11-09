package com.tambo360.app.feature.onboarding.data.dto.request

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UbicacionRequest(
    @SerializedName("provincia") val provincia: String,
    @SerializedName("localidad") val localidad: String
)
