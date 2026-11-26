package com.tambo360.app.feature.home.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "general_costs")
data class GeneralCostsEntity(
    @PrimaryKey
    val id: Int = 1,
    @ColumnInfo(name = "periodo")
    val periodo: String,
    @ColumnInfo(name = "fecha_inicio")
    val fechaInicio: String,
    @ColumnInfo(name = "fecha_fin")
    val fechaFin: String,
    @ColumnInfo(name = "categorias")
    val categorias: List<String>
)
