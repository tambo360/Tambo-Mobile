package com.tambo360.app.feature.home.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "general_cost_items")
data class GeneralCostItemEntity(
    @PrimaryKey
    @ColumnInfo(name = "clave")
    val clave: String,
    @ColumnInfo(name = "periodo")
    val periodo: String,
    @ColumnInfo(name = "anio")
    val anio: Int,
    @ColumnInfo(name = "total")
    val total: Double,
    @ColumnInfo(name = "alquiler")
    val alquiler: Double,
    @ColumnInfo(name = "sueldos")
    val sueldos: Double
)
