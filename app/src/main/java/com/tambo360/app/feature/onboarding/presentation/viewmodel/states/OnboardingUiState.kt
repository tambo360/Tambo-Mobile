package com.tambo360.app.feature.onboarding.presentation.viewmodel.states

import com.tambo360.app.feature.onboarding.data.dto.request.AnimalRequest
import com.tambo360.app.feature.onboarding.data.dto.request.RazaRequest
import com.tambo360.app.feature.onboarding.domain.model.CreateOrganization
import com.tambo360.app.feature.onboarding.domain.model.QuestionnaireResult
import com.tambo360.app.feature.onboarding.presentation.content.ConfigStep
import com.tambo360.app.feature.onboarding.presentation.content.ProductionStatus
import com.tambo360.app.feature.onboarding.presentation.util.ArgentinaProvincias
import com.tambo360.app.feature.onboarding.presentation.util.LocalidadDto
import com.tambo360.app.feature.onboarding.presentation.util.Provincia

data class QuestionnaireFormData(
    val selectedLocationName: String? = null,
    val productionStatus: ProductionStatus = ProductionStatus.NORMAL,
    val milksPerDay: Int? = null,
    val installationType: String? = null,
    val averageLiters: String = "",
    val milkDestination: String? = null,
    val averageDEL: String = "",
    val salePrice: String = "",
    val altoAnimalCount: String = "",
    val altoRationCost: String = "",
    val altoRazas: List<RazaRequest> = emptyList(),
    val bajoAnimalCount: String = "",
    val bajoRationCost: String = "",
    val bajoRazas: List<RazaRequest> = emptyList(),
    val secoAnimalCount: String = "",
    val secoRationCost: String = "",
    val secoRazas: List<RazaRequest> = emptyList(),
    val unicoAnimalCount: String = "",
    val unicoRationCost: String = "",
    val unicoRazas: List<RazaRequest> = emptyList(),
    val unicaSecaAnimalCount: String = "",
    val unicaSecaRationCost: String = "",
    val unicaSecaRazas: List<RazaRequest> = emptyList(),
    val individualAnimales: List<AnimalRequest> = emptyList(),
    val tempAnimalCodigo: String = "",
    val tempAnimalNombre: String = "",
    val tempAnimalSelectedRaza: String? = null,
    val tempAnimalSelectedCategoria: String? = null,
    val tempAnimalSelectedEstado: String? = null,
    val savedUnicoRationCost: String = "",
    val savedUnicaSecaRationCost: String = "",
    val savedAltoRationCost: String = "",
    val savedBajoRationCost: String = "",
    val savedSecoRationCost: String = ""
)

data class OnboardingUiState(
    val loading: Boolean = false,
    val data: CreateOrganization? = null,
    val questionnaireResult: QuestionnaireResult? = null,
    val error: String = "",
    val provincias: List<Provincia> = ArgentinaProvincias.lista,
    val localidades: List<LocalidadDto> = emptyList(),
    val selectedProvincia: Provincia? = null,

    val currentStep: ConfigStep = ConfigStep.LOCATION,
    val formData: QuestionnaireFormData = QuestionnaireFormData()
) {
    val isNextEnabled: Boolean
        get() = when (currentStep) {
            ConfigStep.LOCATION -> {
                selectedProvincia != null && !formData.selectedLocationName.isNullOrBlank()
            }

            ConfigStep.ORDENE -> {
                val hasBasicFields = formData.milksPerDay != null &&
                        !formData.installationType.isNullOrBlank() &&
                        formData.averageLiters.isNotBlank() &&
                        !formData.milkDestination.isNullOrBlank() &&
                        formData.averageDEL.isNotBlank() &&
                        formData.salePrice.isNotBlank()

                val liters = formData.averageLiters.toIntOrNull() ?: 0
                val hasAnsweredStatus = if (formData.averageLiters.isNotBlank() && liters < 2001) {
                    formData.productionStatus != ProductionStatus.NORMAL
                } else {
                    true
                }
                hasBasicFields && hasAnsweredStatus
            }

            ConfigStep.PRODUCTION -> {
                when (formData.productionStatus) {
                    ProductionStatus.ACCEPTED -> formData.individualAnimales.isNotEmpty()
                    ProductionStatus.CANCELED -> {
                        formData.unicoRazas.isNotEmpty() && formData.unicaSecaRazas.isNotEmpty()
                    }

                    ProductionStatus.NORMAL -> {
                        formData.altoRazas.isNotEmpty() &&
                                formData.bajoRazas.isNotEmpty() &&
                                formData.secoRazas.isNotEmpty()
                    }
                }
            }
        }
}
