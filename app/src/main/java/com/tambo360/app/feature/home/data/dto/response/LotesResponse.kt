package com.tambo360.app.feature.home.data.dto.response

import com.google.gson.annotations.SerializedName

data class LotesResponse(
    @SerializedName("success") val success: Boolean = false,
    @SerializedName("message") val message: String = "",
    @SerializedName("data") val data: LotesDataResponse = LotesDataResponse()
)

data class LotesDataResponse(
    @SerializedName("page") val page: Int = 1,
    @SerializedName("limit") val limit: Int = 10,
    @SerializedName("totalLotes") val totalLotes: Int = 0,
    @SerializedName("totalPaginas") val totalPaginas: Int = 0,
    @SerializedName("lotes") val lotes: List<LoteWrapperResponse> = emptyList()
)

data class LoteWrapperResponse(
    @SerializedName("lote") val lote: LoteDetailResponse = LoteDetailResponse(),
    @SerializedName("merma_porcentaje") val mermaPorcentaje: Double = 0.0
)

data class LoteDetailResponse(
    @SerializedName("idLote") val idLote: String = "",
    @SerializedName("numeroLote") val numeroLote: Int = 0,
    @SerializedName("fechaProduccion") val fechaProduccion: String = "",
    @SerializedName("cantidad") val cantidad: Double = 0.0,
    @SerializedName("unidad") val unidad: String = "",
    @SerializedName("estado") val estado: Boolean = false,
    @SerializedName("idProducto") val idProducto: String = "",
    @SerializedName("idEstablecimiento") val idEstablecimiento: String = "",
    @SerializedName("producto") val producto: LoteProductoResponse? = null,
    @SerializedName("idRodeo") val idRodeo: String? = null,
    @SerializedName("cantAnimales") val cantAnimales: Int? = null,
    @SerializedName("destino") val destino: String? = null,
    @SerializedName("tempTanque") val tempTanque: Double? = null,
    @SerializedName("rodeo") val rodeo: LoteRodeoResponse? = null,
    @SerializedName("mermas") val mermas: List<LoteMermaResponse> = emptyList(),
    @SerializedName("costosDirectos") val costosDirectos: List<LoteCostoDirectoResponse> = emptyList(),
    @SerializedName("produccionesIndividuales") val produccionesIndividuales: List<LoteProduccionIndividualResponse> = emptyList(),
    @SerializedName("turno") val turno: String? = null
)

data class LoteProductoResponse(
    @SerializedName("idProducto") val idProducto: String = "",
    @SerializedName("nombre") val nombre: String = "",
    @SerializedName("categoria") val categoria: String = ""
)

data class LoteRodeoResponse(
    @SerializedName("idRodeo") val idRodeo: String = "",
    @SerializedName("tipoRodeo") val tipoRodeo: String = "",
    @SerializedName("costoRacion") val costoRacion: Double = 0.0,
    @SerializedName("cantVacas") val cantVacas: Int = 0
)

data class LoteMermaResponse(
    @SerializedName("idMerma") val idMerma: String = "",
    @SerializedName("tipo") val tipo: String = "",
    @SerializedName("cantidad") val cantidad: Double = 0.0,
    @SerializedName("fechaCreacion") val fechaCreacion: String = ""
)

data class LoteCostoDirectoResponse(
    @SerializedName("idCostoDirecto") val idCostoDirecto: String = "",
    @SerializedName("concepto") val concepto: String = "",
    @SerializedName("monto") val monto: Double = 0.0,
    @SerializedName("observaciones") val observaciones: String? = null,
    @SerializedName("fechaCreacion") val fechaCreacion: String = ""
)

data class LoteProduccionIndividualResponse(
    @SerializedName("idProduccionAnimal") val idProduccionAnimal: String = "",
    @SerializedName("idAnimal") val idAnimal: String = "",
    @SerializedName("litros") val litros: Double = 0.0,
    @SerializedName("estado") val estado: String = ""
)
