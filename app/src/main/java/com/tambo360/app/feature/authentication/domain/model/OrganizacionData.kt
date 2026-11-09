package com.tambo360.app.feature.authentication.domain.model

data class OrganizacionData(
    val idOrganizacionUsuario: String,
    val idOrganizacion: String,
    val idUsuario: String,
    val rol: String,
    val estado: Boolean,
    val fechaCreacion: String,
    val establecimientoOrganizacionUsuarios: List<EstablecimientoData>
)
