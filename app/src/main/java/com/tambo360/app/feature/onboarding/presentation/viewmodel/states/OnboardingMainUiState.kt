package com.tambo360.app.feature.onboarding.presentation.viewmodel.states

sealed class OnboardingMainUiState {
    data object CreateEstablishment : OnboardingMainUiState()
    data class StepByStep(val establishmentId: String = "") : OnboardingMainUiState()
    data object Completed : OnboardingMainUiState()
}
