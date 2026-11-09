package com.tambo360.app.feature.onboarding.data.dto.request

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CreateOrganizationRequest(
    @SerializedName("nombre")
    val nombre: String
)
