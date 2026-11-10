package com.tambo360.app.feature.onboarding.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.tambo360.app.feature.onboarding.presentation.content.CreateEstablishmentContent
import com.tambo360.app.feature.onboarding.presentation.content.StepByStepContent
import com.tambo360.app.feature.onboarding.presentation.viewmodel.OnboardingMainViewModel
import com.tambo360.app.feature.onboarding.presentation.viewmodel.states.OnboardingMainUiState

@Composable
fun OnboardingMainScreen(
    viewModel: OnboardingMainViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    when (val currentState = state) {
        OnboardingMainUiState.CreateEstablishment -> {
            CreateEstablishmentContent(
                goToStep = { viewModel.updateUiState(state = OnboardingMainUiState.StepByStep()) }
            )
        }

        is OnboardingMainUiState.StepByStep -> {
            StepByStepContent(
                onFinishClick = { request ->

                }
            )
        }

        OnboardingMainUiState.Completed -> {

        }
    }
}