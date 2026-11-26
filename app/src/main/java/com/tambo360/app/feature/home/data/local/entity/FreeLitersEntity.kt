package com.tambo360.app.feature.home.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "free_liters")
data class FreeLitersEntity(
    @PrimaryKey
    val id: Int = 1,
    @ColumnInfo(name = "fecha_desde")
    val fechaDesde: String,
    @ColumnInfo(name = "fecha_hasta")
    val fechaHasta: String,
    @ColumnInfo(name = "litros_producidos")
    val litrosProducidos: Double,
    @ColumnInfo(name = "precio_litro")
    val precioLitro: Double,
    @ColumnInfo(name = "costos_generales")
    val costosGenerales: Double,
    @ColumnInfo(name = "litros_libres")
    val litrosLibres: Double
)
