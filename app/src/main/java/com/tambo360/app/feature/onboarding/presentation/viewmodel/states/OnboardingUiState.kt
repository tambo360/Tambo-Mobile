package com.tambo360.app.feature.onboarding.presentation.viewmodel.states

import com.tambo360.app.feature.onboarding.domain.model.CreateOrganization

data class OnboardingUiState(
    val loading: Boolean = false,
    val data: CreateOrganization? = null,
    val error: String = ""
)