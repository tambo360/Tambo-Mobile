package com.tambo360.app.feature.home.domain.model

data class MarketPrice(
    val precioLitro: Double,
    val mes: Int,
    val anio: Int,
    val variacion: Double?,
    val ultimaConsulta: String
)
