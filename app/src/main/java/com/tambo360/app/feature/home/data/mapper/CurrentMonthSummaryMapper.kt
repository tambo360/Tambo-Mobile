package com.tambo360.app.feature.home.data.mapper

import com.tambo360.app.feature.home.data.dto.response.CurrentMonthSummaryResponse
import com.tambo360.app.feature.home.domain.model.CurrentMonthSummary

object CurrentMonthSummaryMapper {

    fun CurrentMonthSummaryResponse.toDomain(): CurrentMonthSummary {
        return CurrentMonthSummary(
            actualLeches = data.actual.leches,
            actualQuesos = data.actual.quesos,
            actualMermas = data.actual.mermas,
            actualCostos = data.actual.costos,
            variacionLeches = data.variaciones.leches,
            variacionQuesos = data.variaciones.quesos,
            variacionMermas = data.variaciones.mermas,
            variacionCostos = data.variaciones.costos,
            mesPrevio = data.mesPrevio
        )
    }
}
