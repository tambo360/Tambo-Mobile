package com.tambo360.app.feature.onboarding.presentation.viewmodel.states

import com.tambo360.app.feature.onboarding.domain.model.CreateOrganization
import com.tambo360.app.feature.onboarding.domain.model.QuestionnaireResult
import com.tambo360.app.feature.onboarding.presentation.util.ArgentinaProvincias
import com.tambo360.app.feature.onboarding.presentation.util.LocalidadDto
import com.tambo360.app.feature.onboarding.presentation.util.Provincia

data class OnboardingUiState(
    val loading: Boolean = false,
    val data: CreateOrganization? = null,
    val questionnaireResult: QuestionnaireResult? = null,
    val error: String = "",
    val provincias: List<Provincia> = ArgentinaProvincias.lista,
    val localidades: List<LocalidadDto> = emptyList(),
    val selectedProvincia: Provincia? = null
)