package com.tambo360.app.feature.authentication.data.dto

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("statusCode")
    val statusCode: Int?,

    @SerializedName("message")
    val message: JsonElement?,

    @SerializedName("success")
    val success: Boolean?
)
