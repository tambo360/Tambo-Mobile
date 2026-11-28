package com.tambo360.app.feature.authentication.domain.model

data class OrganizacionData(
    val idOrganizacion: String,
    val nombre: String,
    val rol: String,
    val establecimientos: List<EstablecimientoData>
)
