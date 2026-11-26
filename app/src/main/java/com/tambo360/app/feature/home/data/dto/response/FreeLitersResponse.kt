package com.tambo360.app.feature.home.data.dto.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class FreeLitersResponse(
    @SerializedName("success") val success: Boolean = false,
    @SerializedName("message") val message: String = "",
    @SerializedName("data") val data: FreeLitersDataResponse = FreeLitersDataResponse()
)

@Serializable
data class FreeLitersDataResponse(
    @SerializedName("periodo") val periodo: FreeLitersPeriodResponse = FreeLitersPeriodResponse(),
    @SerializedName("litrosProducidos") val litrosProducidos: Double = 0.0,
    @SerializedName("precioLitro") val precioLitro: Double = 0.0,
    @SerializedName("costosGenerales") val costosGenerales: Double = 0.0,
    @SerializedName("litrosLibres") val litrosLibres: Double = 0.0
)

@Serializable
data class FreeLitersPeriodResponse(
    @SerializedName("fechaDesde") val fechaDesde: String = "",
    @SerializedName("fechaHasta") val fechaHasta: String = ""
)
