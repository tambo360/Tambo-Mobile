package com.tambo360.app.feature.onboarding.domain.model

data class OrganizationData(
    val idEstablecimiento: String,
    val nombre: String,
    val idOrganizacion: String,
    val localidad: String?,
    val provincia: String?,
    val cuestionarioCompletado: Boolean,
    val fechaCreacion: String
)
