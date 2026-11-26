package com.tambo360.app.feature.home.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dashboard_chart_header")
data class DashboardChartHeaderEntity(
    @PrimaryKey
    @ColumnInfo(name = "tipo")
    val tipo: String,
    @ColumnInfo(name = "has_lote")
    val hasLote: Boolean
)

@Entity(tableName = "dashboard_chart_points")
data class DashboardChartPointEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "tipo")
    val tipo: String,
    @ColumnInfo(name = "orden")
    val orden: Int,
    @ColumnInfo(name = "mes")
    val mes: String,
    @ColumnInfo(name = "anio")
    val anio: Int,
    @ColumnInfo(name = "valor")
    val valor: Double
)
