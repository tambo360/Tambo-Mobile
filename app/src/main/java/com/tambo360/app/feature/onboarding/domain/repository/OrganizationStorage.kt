package com.tambo360.app.feature.onboarding.domain.repository

interface OrganizationStorage {
    fun saveIds(idEst: String, idOrg: String)
    fun getIdEst(): String?
    fun getIdOrg(): String?
    fun clear()
}
