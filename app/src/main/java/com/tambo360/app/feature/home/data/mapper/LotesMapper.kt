package com.tambo360.app.feature.home.data.mapper

import com.tambo360.app.feature.home.data.dto.response.LoteCostoDirectoResponse
import com.tambo360.app.feature.home.data.dto.response.LoteMermaResponse
import com.tambo360.app.feature.home.data.dto.response.LoteProduccionIndividualResponse
import com.tambo360.app.feature.home.data.dto.response.LoteProductoResponse
import com.tambo360.app.feature.home.data.dto.response.LoteRodeoResponse
import com.tambo360.app.feature.home.data.dto.response.LoteWrapperResponse
import com.tambo360.app.feature.home.data.dto.response.LotesResponse
import com.tambo360.app.feature.home.domain.model.LoteCostoDirecto
import com.tambo360.app.feature.home.domain.model.LoteItem
import com.tambo360.app.feature.home.domain.model.LoteMerma
import com.tambo360.app.feature.home.domain.model.LoteProduccionIndividual
import com.tambo360.app.feature.home.domain.model.LoteProducto
import com.tambo360.app.feature.home.domain.model.LoteRodeo

object LotesMapper {

    fun LotesResponse.toDomain(): List<LoteItem> {
        return data.lotes.map { it.toDomain() }
    }

    fun LoteWrapperResponse.toDomain(): LoteItem {
        return LoteItem(
            idLote = lote.idLote,
            numeroLote = lote.numeroLote,
            fechaProduccion = lote.fechaProduccion,
            cantidad = lote.cantidad,
            unidad = lote.unidad,
            estado = lote.estado,
            idProducto = lote.idProducto,
            idEstablecimiento = lote.idEstablecimiento,
            producto = lote.producto!!.toDomain(),
            idRodeo = lote.idRodeo,
            cantAnimales = lote.cantAnimales,
            destino = lote.destino,
            tempTanque = lote.tempTanque,
            rodeo = lote.rodeo?.toDomain(),
            mermas = lote.mermas.map { it.toDomain() },
            costosDirectos = lote.costosDirectos.map { it.toDomain() },
            produccionesIndividuales = lote.produccionesIndividuales.map { it.toDomain() },
            turno = lote.turno,
            mermaPorcentaje = mermaPorcentaje
        )
    }

    fun LoteProductoResponse.toDomain(): LoteProducto {
        return LoteProducto(
            idProducto = idProducto,
            nombre = nombre,
            categoria = categoria
        )
    }

    fun LoteRodeoResponse.toDomain(): LoteRodeo {
        return LoteRodeo(
            idRodeo = idRodeo,
            tipoRodeo = tipoRodeo,
            costoRacion = costoRacion,
            cantVacas = cantVacas
        )
    }

    fun LoteMermaResponse.toDomain(): LoteMerma {
        return LoteMerma(
            idMerma = idMerma,
            tipo = tipo,
            cantidad = cantidad,
            fechaCreacion = fechaCreacion
        )
    }

    fun LoteCostoDirectoResponse.toDomain(): LoteCostoDirecto {
        return LoteCostoDirecto(
            idCostoDirecto = idCostoDirecto,
            concepto = concepto,
            monto = monto,
            observaciones = observaciones,
            fechaCreacion = fechaCreacion
        )
    }

    fun LoteProduccionIndividualResponse.toDomain(): LoteProduccionIndividual {
        return LoteProduccionIndividual(
            idProduccionAnimal = idProduccionAnimal,
            idAnimal = idAnimal,
            litros = litros,
            estado = estado
        )
    }
}
