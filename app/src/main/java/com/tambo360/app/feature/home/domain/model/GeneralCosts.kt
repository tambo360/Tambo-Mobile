package com.tambo360.app.feature.home.domain.model

data class GeneralCosts(
    val periodo: String,
    val fechaInicio: String,
    val fechaFin: String,
    val categorias: List<String>,
    val datos: List<GeneralCostItem>
)

data class GeneralCostItem(
    val periodo: String,
    val clave: String,
    val anio: Int,
    val total: Double,
    val alquiler: Double = 0.0,
    val sueldos: Double = 0.0
)
