package com.tambo360.app.feature.onboarding.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.tambo360.app.feature.onboarding.presentation.content.CreateEstablishmentContent
import com.tambo360.app.feature.onboarding.presentation.content.StepByStepContent
import com.tambo360.app.feature.onboarding.presentation.viewmodel.OnboardingMainViewModel
import com.tambo360.app.feature.onboarding.presentation.viewmodel.OnboardingViewModel
import com.tambo360.app.feature.onboarding.presentation.viewmodel.states.OnboardingMainUiState

@Composable
fun OnboardingMainScreen(
    viewModel: OnboardingMainViewModel = hiltViewModel(),
    stepViewModel: OnboardingViewModel = hiltViewModel(),
    goToHome: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val stepState by stepViewModel.uiState.collectAsState()
    LaunchedEffect(stepState.questionnaireResult) {
        if (stepState.questionnaireResult != null) {
            goToHome()
        }
    }
    when (val currentState = state) {
        OnboardingMainUiState.CreateEstablishment -> {
            CreateEstablishmentContent(
                goToStep = { viewModel.updateUiState(state = OnboardingMainUiState.StepByStep) }
            )
        }

        OnboardingMainUiState.StepByStep -> {
            StepByStepContent(
                viewModel = stepViewModel,
                onFinishClick = { request ->
                    stepViewModel.submitQuestionnaire(request)
                }
            )
        }
    }
}
