package com.tambo360.app.feature.home.domain.model

data class LoteItem(
    val idLote: String,
    val numeroLote: Int,
    val fechaProduccion: String,
    val cantidad: Double,
    val unidad: String,
    val estado: Boolean,
    val idProducto: String,
    val idEstablecimiento: String,
    val producto: LoteProducto,
    val idRodeo: String?,
    val cantAnimales: Int?,
    val destino: String?,
    val tempTanque: Double?,
    val rodeo: LoteRodeo?,
    val mermas: List<LoteMerma>,
    val costosDirectos: List<LoteCostoDirecto>,
    val produccionesIndividuales: List<LoteProduccionIndividual>,
    val turno: String?,
    val mermaPorcentaje: Double
)

data class LoteProducto(
    val idProducto: String,
    val nombre: String,
    val categoria: String
)

data class LoteRodeo(
    val idRodeo: String,
    val tipoRodeo: String,
    val costoRacion: Double,
    val cantVacas: Int
)

data class LoteMerma(
    val idMerma: String,
    val tipo: String,
    val cantidad: Double,
    val fechaCreacion: String
)

data class LoteCostoDirecto(
    val idCostoDirecto: String,
    val concepto: String,
    val monto: Double,
    val observaciones: String?,
    val fechaCreacion: String
)

data class LoteProduccionIndividual(
    val idProduccionAnimal: String,
    val idAnimal: String,
    val litros: Double,
    val estado: String
)
