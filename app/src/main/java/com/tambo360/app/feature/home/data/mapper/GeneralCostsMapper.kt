package com.tambo360.app.feature.home.data.mapper

import com.tambo360.app.feature.home.data.dto.response.GeneralCostItemResponse
import com.tambo360.app.feature.home.data.dto.response.GeneralCostsResponse
import com.tambo360.app.feature.home.domain.model.GeneralCostItem
import com.tambo360.app.feature.home.domain.model.GeneralCosts

object GeneralCostsMapper {
    fun GeneralCostsResponse.toDomain(): GeneralCosts {
        return GeneralCosts(
            periodo = data.periodo,
            fechaInicio = data.fechaInicio,
            fechaFin = data.fechaFin,
            categorias = data.categorias,
            datos = data.datos.map { it.toDomain() }
        )
    }

    fun GeneralCostItemResponse.toDomain(): GeneralCostItem {
        return GeneralCostItem(
            periodo = periodo,
            clave = clave,
            anio = anio,
            total = total,
            alquiler = alquiler,
            sueldos = sueldos
        )
    }
}
