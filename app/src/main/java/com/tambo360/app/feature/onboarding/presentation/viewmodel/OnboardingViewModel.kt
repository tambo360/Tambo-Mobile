package com.tambo360.app.feature.onboarding.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tambo360.app.core.network.NetworkResult
import com.tambo360.app.core.util.JsonUtils
import com.tambo360.app.feature.onboarding.data.dto.request.CreateOrganizationRequest
import com.tambo360.app.feature.onboarding.data.dto.request.QuestionnaireRequest
import com.tambo360.app.feature.onboarding.data.dto.request.UbicacionRequest
import com.tambo360.app.feature.onboarding.domain.repository.OnboardingRepository
import com.tambo360.app.feature.onboarding.domain.repository.OrganizationStorage
import com.tambo360.app.feature.onboarding.presentation.content.ConfigStep
import com.tambo360.app.feature.onboarding.presentation.content.ProductionStatus
import com.tambo360.app.feature.onboarding.presentation.util.GeorefProvider
import com.tambo360.app.feature.onboarding.presentation.util.Provincia
import com.tambo360.app.feature.onboarding.presentation.util.QuestionnaireBuilderUtil
import com.tambo360.app.feature.onboarding.presentation.util.TrackingType
import com.tambo360.app.feature.onboarding.presentation.util.orEmptyString
import com.tambo360.app.feature.onboarding.presentation.util.orZero
import com.tambo360.app.feature.onboarding.presentation.util.toDoubleOrDefault
import com.tambo360.app.feature.onboarding.presentation.util.toIntOrDefault
import com.tambo360.app.feature.onboarding.presentation.viewmodel.states.OnboardingUiState
import com.tambo360.app.feature.onboarding.presentation.viewmodel.states.QuestionnaireFormData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val repository: OnboardingRepository,
    private val orgStorage: OrganizationStorage
) : ViewModel() {

    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState get() = _uiState.asStateFlow()

    fun createOrganization(name: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = "") }
            val request = CreateOrganizationRequest(nombre = name)
            when (val result = repository.createOrganization(request)) {
                is NetworkResult.Success -> {
                    val orgData = result.data.data
                    orgStorage.saveIds(
                        idEst = orgData.idEstablecimiento,
                        idOrg = orgData.idOrganizacion
                    )
                    _uiState.update { it.copy(loading = false, data = result.data) }
                }

                is NetworkResult.Failed -> {
                    val cleanMessage = JsonUtils.getErrorMessage(result.message)
                    _uiState.update { it.copy(loading = false, error = cleanMessage) }
                }
            }
        }
    }

    fun submitQuestionnaire(request: QuestionnaireRequest) {
        viewModelScope.launch {
            val idOrg = orgStorage.getIdOrg()
            val idEst = orgStorage.getIdEst()
            if (idOrg.isNullOrBlank() || idEst.isNullOrBlank()) {
                _uiState.update {
                    it.copy(
                        loading = false,
                        error = "No se encontraron los identificadores de organización o establecimiento."
                    )
                }
                return@launch
            }
            _uiState.update { it.copy(loading = true, error = "") }
            when (val result = repository.submitQuestionnaire(
                idOrg = idOrg,
                idEst = idEst,
                request = request
            )) {
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

    fun onNextStep() {
        val next = when (_uiState.value.currentStep) {
            ConfigStep.LOCATION -> ConfigStep.ORDENE
            ConfigStep.ORDENE -> ConfigStep.PRODUCTION
            ConfigStep.PRODUCTION -> ConfigStep.PRODUCTION
        }
        _uiState.update { it.copy(currentStep = next) }
    }

    fun onPreviousStep() {
        val prev = when (_uiState.value.currentStep) {
            ConfigStep.PRODUCTION -> ConfigStep.ORDENE
            ConfigStep.ORDENE -> ConfigStep.LOCATION
            ConfigStep.LOCATION -> ConfigStep.LOCATION
        }
        _uiState.update { it.copy(currentStep = prev) }
    }

    fun updateFormData(update: (QuestionnaireFormData) -> QuestionnaireFormData) {
        _uiState.update { it.copy(formData = update(it.formData)) }
    }

    fun onProvinciaSelected(provincia: Provincia) {
        _uiState.update {
            it.copy(
                selectedProvincia = provincia,
                loading = true,
                error = "",
                formData = it.formData.copy(selectedLocationName = null)
            )
        }
        viewModelScope.launch {
            val result = GeorefProvider.fetchLocalidades(provincia.id)
            result.fold(
                onSuccess = { listaLocalidades ->
                    _uiState.update { it.copy(loading = false, localidades = listaLocalidades) }
                },
                onFailure = { throwable ->
                    _uiState.update {
                        it.copy(
                            loading = false,
                            error = throwable.message ?: "Error al obtener localidades"
                        )
                    }
                }
            )
        }
    }

    fun generateFinalRequest(): QuestionnaireRequest {
        val form = _uiState.value.formData
        val provinciaName = _uiState.value.selectedProvincia?.nombre.orEmpty()

        val trackingType = QuestionnaireBuilderUtil.getTrackingType(form.productionStatus)
        val herdList = when (form.productionStatus) {
            ProductionStatus.NORMAL -> QuestionnaireBuilderUtil.buildHerdList(
                trackingType,
                form.savedAltoRationCost.ifBlank { form.altoRationCost },
                form.altoRazas,
                form.savedBajoRationCost.ifBlank { form.bajoRationCost },
                form.bajoRazas,
                form.savedSecoRationCost.ifBlank { form.secoRationCost },
                form.secoRazas
            )

            ProductionStatus.CANCELED -> QuestionnaireBuilderUtil.buildSingleHerdList(
                trackingType,
                form.savedUnicoRationCost.ifBlank { form.unicoRationCost },
                form.unicoRazas,
                form.savedUnicaSecaRationCost.ifBlank { form.unicaSecaRationCost },
                form.unicaSecaRazas
            )

            else -> null
        }

        return QuestionnaireRequest(
            tipoSeguimiento = trackingType.apiValue,
            cantOrdenie = form.milksPerDay.orZero(),
            tipoOrdenie = form.installationType.orEmptyString(),
            promDEL = form.averageDEL.toIntOrDefault(),
            promLitros = form.averageLiters.toDoubleOrDefault(),
            ventaLeche = form.milkDestination.orEmptyString(),
            precioLitro = form.salePrice.toDoubleOrDefault(),
            ubicacion = UbicacionRequest(
                provincia = provinciaName,
                localidad = form.selectedLocationName.orEmptyString()
            ),
            rodeos = herdList,
            animales = if (trackingType is TrackingType.Individual) form.individualAnimales else null
        )
    }
}
