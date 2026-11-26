package com.tambo360.app.feature.home.domain.model

data class CurrentMonthSummary(
    val actualLeches: Double,
    val actualQuesos: Double,
    val actualMermas: Double,
    val actualCostos: Double,
    val variacionLeches: Double?,
    val variacionQuesos: Double?,
    val variacionMermas: Double?,
    val variacionCostos: Double?,
    val mesPrevio: String
)
