package com.tambo360.app.feature.authentication.domain.model

data class EstablecimientoData(
    val idEstablecimientoOrganizacionUsuario: String,
    val idEstablecimiento: String,
    val idOrganizacionUsuario: String,
    val rol: String,
    val estado: Boolean,
    val fechaCreacion: String
)
