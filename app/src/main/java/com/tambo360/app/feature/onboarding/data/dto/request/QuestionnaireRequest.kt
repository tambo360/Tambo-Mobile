package com.tambo360.app.feature.onboarding.data.dto.request

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionnaireRequest(
    @SerializedName("TipoSeguimiento") val tipoSeguimiento: String,
    @SerializedName("cantOrdenie") val cantOrdenie: Int,
    @SerializedName("tipoOrdenie") val tipoOrdenie: String,
    @SerializedName("promDEL") val promDEL: Int,
    @SerializedName("promLitros") val promLitros: Double,
    @SerializedName("ventaLeche") val ventaLeche: String,
    @SerializedName("precioLitro") val precioLitro: Double,
    @SerializedName("ubicacion") val ubicacion: UbicacionRequest,
    @SerializedName("rodeos") val rodeos: List<RodeoRequest>? = null,
    @SerializedName("animales") val animales: List<AnimalRequest>? = null
)
