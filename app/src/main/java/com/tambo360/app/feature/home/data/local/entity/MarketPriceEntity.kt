package com.tambo360.app.feature.home.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "market_price")
data class MarketPriceEntity(
    @PrimaryKey
    val id: Int = 1,
    @ColumnInfo(name = "precio_litro")
    val precioLitro: Double,
    @ColumnInfo(name = "mes")
    val mes: Int,
    @ColumnInfo(name = "anio")
    val anio: Int,
    @ColumnInfo(name = "variacion")
    val variacion: Double?,
    @ColumnInfo(name = "ultima_consulta")
    val ultimaConsulta: String
)
