package com.tambo360.app.feature.onboarding.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tambo360.app.core.data.TokenRepository
import com.tambo360.app.core.network.NetworkResult
import com.tambo360.app.core.util.JsonUtils
import com.tambo360.app.feature.onboarding.data.dto.request.CreateOrganizationRequest
import com.tambo360.app.feature.onboarding.data.dto.request.QuestionnaireRequest
import com.tambo360.app.feature.onboarding.domain.repository.OnboardingRepository
import com.tambo360.app.feature.onboarding.presentation.util.GeorefProvider
import com.tambo360.app.feature.onboarding.presentation.util.Provincia
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
    private val tokenRepo: TokenRepository
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

    fun onProvinciaSelected(provincia: Provincia) {
        _uiState.update { it.copy(selectedProvincia = provincia, loading = true, error = "") }
        viewModelScope.launch {
            val result = GeorefProvider.fetchLocalidades(provincia.id)
            result.fold(
                onSuccess = { listaLocalidades ->
                    _uiState.update {
                        it.copy(loading = false, localidades = listaLocalidades)
                    }
                },
                onFailure = { throwable ->
                    _uiState.update {
                        it.copy(loading = false, error = throwable.message ?: "Error al obtener localidades")
                    }
                }
            )
        }
    }

    fun submitQuestionnaire(request: QuestionnaireRequest) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = "") }

            when (val result = repository.submitQuestionnaire(request)) {
                is NetworkResult.Success -> {
                    _uiState.update { it.copy(loading = false, questionnaireResult = result.data) }
                }

                is NetworkResult.Failed -> {
                    val cleanMessage = JsonUtils.getErrorMessage(result.message)
                    _uiState.update { it.copy(loading = false, error = cleanMessage) }
                }
            }
        }
    }

}
