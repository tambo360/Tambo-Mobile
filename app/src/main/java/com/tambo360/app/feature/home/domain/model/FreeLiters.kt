package com.tambo360.app.feature.home.domain.model

data class FreeLiters(
    val fechaDesde: String,
    val fechaHasta: String,
    val litrosProducidos: Double,
    val precioLitro: Double,
    val costosGenerales: Double,
    val litrosLibres: Double
)
