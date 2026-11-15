package com.tambo360.app.feature.onboarding.domain.repository

interface OrganizationStorage {
    fun saveIds(idEstablecimiento: String, idOrganizacion: String)
    fun getIdEstablecimiento(): String?
    fun getIdOrganizacion(): String?
    fun clear()
}
