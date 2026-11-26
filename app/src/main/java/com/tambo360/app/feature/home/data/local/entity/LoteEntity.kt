package com.tambo360.app.feature.home.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "home_lotes")
data class LoteEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_lote")
    val idLote: String,
    @ColumnInfo(name = "orden")
    val orden: Int,
    @ColumnInfo(name = "numero_lote")
    val numeroLote: Int,
    @ColumnInfo(name = "fecha_produccion")
    val fechaProduccion: String,
    @ColumnInfo(name = "cantidad")
    val cantidad: Double,
    @ColumnInfo(name = "unidad")
    val unidad: String,
    @ColumnInfo(name = "estado")
    val estado: Boolean,
    @ColumnInfo(name = "id_producto")
    val idProducto: String,
    @ColumnInfo(name = "id_establecimiento")
    val idEstablecimiento: String,
    @Embedded(prefix = "producto_")
    val producto: LoteProductoEmbedded,
    @ColumnInfo(name = "id_rodeo")
    val idRodeo: String?,
    @ColumnInfo(name = "cant_animales")
    val cantAnimales: Int?,
    @ColumnInfo(name = "destino")
    val destino: String?,
    @ColumnInfo(name = "temp_tanque")
    val tempTanque: Double?,
    @Embedded(prefix = "rodeo_")
    val rodeo: LoteRodeoEmbedded?,
    @ColumnInfo(name = "mermas")
    val mermas: List<LoteMermaRaw>,
    @ColumnInfo(name = "costos_directos")
    val costosDirectos: List<LoteCostoDirectoRaw>,
    @ColumnInfo(name = "producciones_individuales")
    val produccionesIndividuales: List<LoteProduccionIndividualRaw>,
    @ColumnInfo(name = "turno")
    val turno: String?,
    @ColumnInfo(name = "merma_porcentaje")
    val mermaPorcentaje: Double
)

data class LoteProductoEmbedded(
    @ColumnInfo(name = "id")
    val idProducto: String,
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "categoria")
    val categoria: String
)

data class LoteRodeoEmbedded(
    @ColumnInfo(name = "id")
    val idRodeo: String,
    @ColumnInfo(name = "tipo")
    val tipoRodeo: String,
    @ColumnInfo(name = "costo_racion")
    val costoRacion: Double,
    @ColumnInfo(name = "cant_vacas")
    val cantVacas: Int
)

data class LoteMermaRaw(
    val idMerma: String,
    val tipo: String,
    val cantidad: Double,
    val fechaCreacion: String
)

data class LoteCostoDirectoRaw(
    val idCostoDirecto: String,
    val concepto: String,
    val monto: Double,
    val observaciones: String?,
    val fechaCreacion: String
)

data class LoteProduccionIndividualRaw(
    val idProduccionAnimal: String,
    val idAnimal: String,
    val litros: Double,
    val estado: String
)
