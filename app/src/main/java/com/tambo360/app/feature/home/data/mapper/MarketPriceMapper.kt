package com.tambo360.app.feature.home.data.mapper

import com.tambo360.app.feature.home.data.dto.response.MarketPriceResponse
import com.tambo360.app.feature.home.domain.model.MarketPrice

object MarketPriceMapper {

    fun MarketPriceResponse.toDomain(): MarketPrice {
        return MarketPrice(
            precioLitro = data.precioLitro,
            mes = data.mes,
            anio = data.anio,
            variacion = data.variacion,
            ultimaConsulta = data.ultimaConsulta
        )
    }
}
