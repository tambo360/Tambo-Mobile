package com.tambo360.app.feature.home.domain.model

import com.tambo360.app.feature.home.data.mapper.DashboardChartType

data class DashboardChart(
    val tipo: DashboardChartType,
    val hasLote: Boolean,
    val resultado: List<DashboardChartPoint>
)

data class DashboardChartPoint(
    val mes: String,
    val anio: Int,
    val valor: Double
)
