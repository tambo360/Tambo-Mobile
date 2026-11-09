package com.tambo360.app.feature.onboarding.domain.repository

import com.tambo360.app.core.network.NetworkResult
import com.tambo360.app.feature.onboarding.data.dto.request.CreateOrganizationRequest
import com.tambo360.app.feature.onboarding.data.dto.request.QuestionnaireRequest
import com.tambo360.app.feature.onboarding.domain.model.CreateOrganization
import com.tambo360.app.feature.onboarding.domain.model.QuestionnaireResult

interface OnboardingRepository {

    suspend fun createOrganization(request: CreateOrganizationRequest): NetworkResult<CreateOrganization>

    suspend fun submitQuestionnaire(request: QuestionnaireRequest): NetworkResult<QuestionnaireResult>

}