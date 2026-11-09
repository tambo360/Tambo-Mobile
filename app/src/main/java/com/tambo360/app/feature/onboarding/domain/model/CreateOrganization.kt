package com.tambo360.app.feature.onboarding.domain.model

data class CreateOrganization(
    val success: Boolean,
    val message: String,
    val data: OrganizationData
)
