package com.tambo360.app.feature.home.data.mapper

import com.tambo360.app.feature.home.data.dto.response.FreeLitersResponse
import com.tambo360.app.feature.home.domain.model.FreeLiters

object FreeLitersMapper {

    fun FreeLitersResponse.toDomain(): FreeLiters {
        return FreeLiters(
            fechaDesde = data.periodo.fechaDesde,
            fechaHasta = data.periodo.fechaHasta,
            litrosProducidos = data.litrosProducidos,
            precioLitro = data.precioLitro,
            costosGenerales = data.costosGenerales,
            litrosLibres = data.litrosLibres
        )
    }
}
