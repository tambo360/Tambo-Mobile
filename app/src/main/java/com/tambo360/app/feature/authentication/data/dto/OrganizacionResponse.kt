package com.tambo360.app.feature.authentication.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class OrganizacionResponse(
    @SerializedName("rol") val rol: String,
    @SerializedName("organizacion") val organizacion: OrganizacionDetalleResponse
)
@Serializable
data class OrganizacionDetalleResponse(
    @SerializedName("idOrganizacion") val idOrganizacion: String,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("establecimientos") val establecimientos: List<EstablecimientoResponse>? = null
)
