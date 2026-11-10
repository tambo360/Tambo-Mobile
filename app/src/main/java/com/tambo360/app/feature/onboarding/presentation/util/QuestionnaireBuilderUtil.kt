package com.tambo360.app.feature.onboarding.presentation.util

import com.tambo360.app.feature.onboarding.data.dto.request.RazaRequest
import com.tambo360.app.feature.onboarding.data.dto.request.RodeoRequest
import com.tambo360.app.feature.onboarding.presentation.content.ProductionStatus

object QuestionnaireBuilderUtil {
    fun getTrackingType(status: ProductionStatus): TrackingType {
        return when (status) {
            ProductionStatus.NORMAL -> TrackingType.Herd
            ProductionStatus.CANCELED -> TrackingType.SingleHerd
            ProductionStatus.ACCEPTED -> TrackingType.Individual
        }
    }

    fun buildHerdList(
        trackingType: TrackingType,
        highRationCost: String,
        highBreeds: List<RazaRequest>,
        lowRationCost: String,
        lowBreeds: List<RazaRequest>,
        dryRationCost: String,
        dryBreeds: List<RazaRequest>
    ): List<RodeoRequest>? {
        if (trackingType !is TrackingType.Herd) return null

        val herds = mutableListOf<RodeoRequest>()

        if (highBreeds.isNotEmpty()) {
            herds.add(
                RodeoRequest(
                    tipoRodeo = "ALTA_PRODUCCION",
                    costoRacion = highRationCost.toDoubleOrNull() ?: 0.0,
                    razas = highBreeds
                )
            )
        }
        if (lowBreeds.isNotEmpty()) {
            herds.add(
                RodeoRequest(
                    tipoRodeo = "BAJA_PRODUCCION",
                    costoRacion = lowRationCost.toDoubleOrNull() ?: 0.0,
                    razas = lowBreeds
                )
            )
        }
        if (dryBreeds.isNotEmpty()) {
            herds.add(
                RodeoRequest(
                    tipoRodeo = "VACAS_SECAS",
                    costoRacion = dryRationCost.toDoubleOrNull() ?: 0.0,
                    razas = dryBreeds
                )
            )
        }

        return herds.takeIf { it.isNotEmpty() }
    }
}
