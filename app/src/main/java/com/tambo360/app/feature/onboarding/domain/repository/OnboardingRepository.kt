package com.tambo360.app.feature.onboarding.domain.repository

import com.tambo360.app.core.network.NetworkResult
import com.tambo360.app.feature.onboarding.data.dto.request.CreateOrganizationRequest
import com.tambo360.app.feature.onboarding.domain.model.CreateOrganization

interface OnboardingRepository {

    suspend fun createOrganization(request: CreateOrganizationRequest): NetworkResult<CreateOrganization>

}