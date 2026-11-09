package com.tambo360.app.feature.authentication.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class EstablecimientoResponse(
    val idEstablecimientoOrganizacionUsuario: String,
    val idEstablecimiento: String,
    val idOrganizacionUsuario: String,
    val rol: String,
    val estado: Boolean,
    val fechaCreacion: String
)
