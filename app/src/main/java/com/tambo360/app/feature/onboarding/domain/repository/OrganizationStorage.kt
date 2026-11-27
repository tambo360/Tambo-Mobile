package com.tambo360.app.feature.onboarding.domain.repository

interface OrganizationStorage {
    fun saveIds(idEst: String, idOrg: String)
    fun getIdEst(): String?
    fun getIdOrg(): String?
    fun saveName(name: String)
    fun getName(): String?
    fun clear()
    fun setOnboardingCompleted(completed: Boolean)
    fun isOnboardingCompleted(): Boolean
}
