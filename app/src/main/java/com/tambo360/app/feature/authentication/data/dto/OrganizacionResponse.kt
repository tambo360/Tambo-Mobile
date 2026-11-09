package com.tambo360.app.feature.authentication.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class OrganizacionResponse(
    val idOrganizacionUsuario: String,
    val idOrganizacion: String,
    val idUsuario: String,
    val rol: String,
    val estado: Boolean,
    val fechaCreacion: String,
    val establecimientoOrganizacionUsuarios: List<EstablecimientoResponse>
)
