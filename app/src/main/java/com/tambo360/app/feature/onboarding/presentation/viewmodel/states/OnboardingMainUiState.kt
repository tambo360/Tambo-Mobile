package com.tambo360.app.feature.onboarding.presentation.viewmodel.states

sealed class OnboardingMainUiState {
    data object CreateEstablishment : OnboardingMainUiState()
    data object StepByStep : OnboardingMainUiState()
}
