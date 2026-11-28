package com.tambo360.app.feature.home.data.dto.response

import com.google.gson.annotations.SerializedName

data class MarketPriceResponse(
    @SerializedName("statusCode") val statusCode: Int = 200,
    @SerializedName("success") val success: Boolean = false,
    @SerializedName("message") val message: String = "",
    @SerializedName("data") val data: MarketPriceDataResponse = MarketPriceDataResponse()
)

data class MarketPriceDataResponse(
    @SerializedName("precioLitro") val precioLitro: Double = 0.0,
    @SerializedName("mes") val mes: Int = 0,
    @SerializedName("anio") val anio: Int = 0,
    @SerializedName("variacion") val variacion: Double? = null,
    @SerializedName("ultimaConsulta") val ultimaConsulta: String = ""
)
