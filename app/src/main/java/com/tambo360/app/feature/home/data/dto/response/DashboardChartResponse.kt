package com.tambo360.app.feature.home.data.dto.response

import com.google.gson.annotations.SerializedName

data class DashboardChartResponse(
    @SerializedName("success") val success: Boolean = false,
    @SerializedName("message") val message: String = "",
    @SerializedName("data") val data: DashboardChartDataResponse = DashboardChartDataResponse()
)

data class DashboardChartDataResponse(
    @SerializedName("resultado") val resultado: List<DashboardChartPointResponse> = emptyList(),
    @SerializedName("Lote") val lote: Boolean = false
)

data class DashboardChartPointResponse(
    @SerializedName("mes") val mes: String = "",
    @SerializedName("anio") val anio: Int = 0,
    @SerializedName("valor") val valor: Double = 0.0
)
