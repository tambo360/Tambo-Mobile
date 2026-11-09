package com.tambo360.app.feature.onboarding.data.dto.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class OrganizationDataResponse(
    @SerializedName("idEstablecimiento")
    val idEstablecimiento: String,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("idOrganizacion")
    val idOrganizacion: String,
    @SerializedName("localidad")
    val localidad: String?,
    @SerializedName("provincia")
    val provincia: String?,
    @SerializedName("cuestionarioCompletado")
    val cuestionarioCompletado: Boolean,
    @SerializedName("fechaCreacion")
    val fechaCreacion: String
)
