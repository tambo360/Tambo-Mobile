package com.tambo360.app.feature.home.data.mapper

import com.tambo360.app.feature.home.data.dto.response.DashboardChartPointResponse
import com.tambo360.app.feature.home.data.dto.response.DashboardChartResponse
import com.tambo360.app.feature.home.domain.model.DashboardChart
import com.tambo360.app.feature.home.domain.model.DashboardChartPoint

enum class DashboardChartType(
    val key: String,
    val producto: String,
    val metrica: String
) {
    LECHES_CANTIDAD(
        key = "leches_cantidad",
        producto = "leches",
        metrica = "cantidad"
    ),
    LECHES_MERMAS(
        key = "leches_mermas",
        producto = "leches",
        metrica = "mermas"
    )
}

object DashboardChartMapper {

    fun DashboardChartResponse.toDomain(tipo: DashboardChartType): DashboardChart {
        return DashboardChart(
            tipo = tipo,
            hasLote = data.lote,
            resultado = data.resultado.map { it.toDomain() }
        )
    }

    fun DashboardChartPointResponse.toDomain(): DashboardChartPoint {
        return DashboardChartPoint(
            mes = mes,
            anio = anio,
            valor = valor
        )
    }
}
