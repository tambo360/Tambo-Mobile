package com.tambo360.app.feature.home.data.dto.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentMonthSummaryResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: CurrentMonthDataResponse
)

@Serializable
data class CurrentMonthDataResponse(
    @SerializedName("actual") val actual: CurrentMonthActualResponse,
    @SerializedName("variaciones") val variaciones: CurrentMonthVariationsResponse,
    @SerializedName("mesPrevio") val mesPrevio: String
)

@Serializable
data class CurrentMonthActualResponse(
    @SerializedName("leches") val leches: Double,
    @SerializedName("quesos") val quesos: Double,
    @SerializedName("mermas") val mermas: Double,
    @SerializedName("costos") val costos: Double
)

@Serializable
data class CurrentMonthVariationsResponse(
    @SerializedName("leches") val leches: Double?,
    @SerializedName("quesos") val quesos: Double?,
    @SerializedName("mermas") val mermas: Double?,
    @SerializedName("costos") val costos: Double?
)
