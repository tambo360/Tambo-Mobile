package com.tambo360.app.feature.onboarding.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.tambo360.app.feature.onboarding.presentation.viewmodel.states.OnboardingMainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OnboardingMainViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState =
        MutableStateFlow<OnboardingMainUiState>(OnboardingMainUiState.CreateEstablishment)
    val uiState get() = _uiState.asStateFlow()

    fun updateUiState(state: OnboardingMainUiState) {
        _uiState.update { state }
    }
}
