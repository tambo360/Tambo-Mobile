package com.tambo360.app.feature.home.data.dto.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GeneralCostsResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: GeneralCostsDataResponse
)

@Serializable
data class GeneralCostsDataResponse(
    @SerializedName("periodo") val periodo: String,
    @SerializedName("fechaInicio") val fechaInicio: String,
    @SerializedName("fechaFin") val fechaFin: String,
    @SerializedName("categorias") val categorias: List<String>,
    @SerializedName("datos") val datos: List<GeneralCostItemResponse>
)

@Serializable
data class GeneralCostItemResponse(
    @SerializedName("periodo") val periodo: String = "",
    @SerializedName("clave") val clave: String = "",
    @SerializedName("anio") val anio: Int = 0,
    @SerializedName("total") val total: Double = 0.0,
    @SerializedName("alquiler") val alquiler: Double = 0.0,
    @SerializedName("sueldos") val sueldos: Double = 0.0
)
