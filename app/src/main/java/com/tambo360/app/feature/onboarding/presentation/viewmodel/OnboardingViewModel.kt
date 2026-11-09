package com.tambo360.app.feature.onboarding.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tambo360.app.core.data.TokenRepository
import com.tambo360.app.core.network.NetworkResult
import com.tambo360.app.core.util.JsonUtils
import com.tambo360.app.feature.onboarding.data.dto.request.CreateOrganizationRequest
import com.tambo360.app.feature.onboarding.domain.repository.OnboardingRepository
import com.tambo360.app.feature.onboarding.presentation.viewmodel.states.OnboardingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val repository: OnboardingRepository,
    private val tokenRepo : TokenRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState get() = _uiState.asStateFlow()

    fun createOrganization(name: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = "") }
            val request = CreateOrganizationRequest(nombre = name)
            val tk = tokenRepo.getToken().toString()
            when (val result = repository.createOrganization(request)) {
                is NetworkResult.Success -> {
                    _uiState.update { it.copy(loading = false, data = result.data) }
                }
                is NetworkResult.Failed -> {
                    val cleanMessage = JsonUtils.getErrorMessage(result.message)
                    _uiState.update { it.copy(loading = false, error = cleanMessage) }
                }
            }
        }
    }
}
