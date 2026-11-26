package com.tambo360.app.feature.home.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_month_summary")
data class CurrentMonthSummaryEntity(
    @PrimaryKey
    val id: Int = 1,
    @ColumnInfo(name = "actual_leches")
    val actualLeches: Double,
    @ColumnInfo(name = "actual_quesos")
    val actualQuesos: Double,
    @ColumnInfo(name = "actual_mermas")
    val actualMermas: Double,
    @ColumnInfo(name = "actual_costos")
    val actualCostos: Double,
    @ColumnInfo(name = "variacion_leches")
    val variacionLeches: Double?,
    @ColumnInfo(name = "variacion_quesos")
    val variacionQuesos: Double?,
    @ColumnInfo(name = "variacion_mermas")
    val variacionMermas: Double?,
    @ColumnInfo(name = "variacion_costos")
    val variacionCostos: Double?,
    @ColumnInfo(name = "mes_previo")
    val mesPrevio: String
)
