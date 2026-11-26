package com.tambo360.app.feature.home.data.mapper

import com.tambo360.app.feature.home.data.local.entity.CurrentMonthSummaryEntity
import com.tambo360.app.feature.home.data.local.entity.DashboardChartHeaderEntity
import com.tambo360.app.feature.home.data.local.entity.DashboardChartPointEntity
import com.tambo360.app.feature.home.data.local.entity.FreeLitersEntity
import com.tambo360.app.feature.home.data.local.entity.GeneralCostItemEntity
import com.tambo360.app.feature.home.data.local.entity.GeneralCostsEntity
import com.tambo360.app.feature.home.data.local.entity.LoteCostoDirectoRaw
import com.tambo360.app.feature.home.data.local.entity.LoteEntity
import com.tambo360.app.feature.home.data.local.entity.LoteMermaRaw
import com.tambo360.app.feature.home.data.local.entity.LoteProduccionIndividualRaw
import com.tambo360.app.feature.home.data.local.entity.LoteProductoEmbedded
import com.tambo360.app.feature.home.data.local.entity.LoteRodeoEmbedded
import com.tambo360.app.feature.home.data.local.entity.MarketPriceEntity
import com.tambo360.app.feature.home.domain.model.CurrentMonthSummary
import com.tambo360.app.feature.home.domain.model.DashboardChart
import com.tambo360.app.feature.home.domain.model.DashboardChartPoint
import com.tambo360.app.feature.home.domain.model.FreeLiters
import com.tambo360.app.feature.home.domain.model.GeneralCostItem
import com.tambo360.app.feature.home.domain.model.GeneralCosts
import com.tambo360.app.feature.home.domain.model.LoteCostoDirecto
import com.tambo360.app.feature.home.domain.model.LoteItem
import com.tambo360.app.feature.home.domain.model.LoteMerma
import com.tambo360.app.feature.home.domain.model.LoteProduccionIndividual
import com.tambo360.app.feature.home.domain.model.LoteProducto
import com.tambo360.app.feature.home.domain.model.LoteRodeo
import com.tambo360.app.feature.home.domain.model.MarketPrice

object HomeDataMapper {
    fun mapToEntity(domain: CurrentMonthSummary): CurrentMonthSummaryEntity {
        return CurrentMonthSummaryEntity(
            id = 1,
            actualLeches = domain.actualLeches,
            actualQuesos = domain.actualQuesos,
            actualMermas = domain.actualMermas,
            actualCostos = domain.actualCostos,
            variacionLeches = domain.variacionLeches,
            variacionQuesos = domain.variacionQuesos,
            variacionMermas = domain.variacionMermas,
            variacionCostos = domain.variacionCostos,
            mesPrevio = domain.mesPrevio
        )
    }

    fun mapToDomain(entity: CurrentMonthSummaryEntity): CurrentMonthSummary {
        return CurrentMonthSummary(
            actualLeches = entity.actualLeches,
            actualQuesos = entity.actualQuesos,
            actualMermas = entity.actualMermas,
            actualCostos = entity.actualCostos,
            variacionLeches = entity.variacionLeches,
            variacionQuesos = entity.variacionQuesos,
            variacionMermas = entity.variacionMermas,
            variacionCostos = entity.variacionCostos,
            mesPrevio = entity.mesPrevio
        )
    }
    fun mapToEntity(domain: GeneralCosts): Pair<GeneralCostsEntity, List<GeneralCostItemEntity>> {
        val header = GeneralCostsEntity(
            id = 1,
            periodo = domain.periodo,
            fechaInicio = domain.fechaInicio,
            fechaFin = domain.fechaFin,
            categorias = domain.categorias
        )
        val items = domain.datos.map { item ->
            GeneralCostItemEntity(
                clave = item.clave,
                periodo = item.periodo,
                anio = item.anio,
                total = item.total,
                alquiler = item.alquiler,
                sueldos = item.sueldos
            )
        }
        return header to items
    }

    fun mapToDomain(
        header: GeneralCostsEntity,
        items: List<GeneralCostItemEntity>
    ): GeneralCosts {
        return GeneralCosts(
            periodo = header.periodo,
            fechaInicio = header.fechaInicio,
            fechaFin = header.fechaFin,
            categorias = header.categorias,
            datos = items.map { item ->
                GeneralCostItem(
                    periodo = item.periodo,
                    clave = item.clave,
                    anio = item.anio,
                    total = item.total,
                    alquiler = item.alquiler,
                    sueldos = item.sueldos
                )
            }
        )
    }
    fun mapToEntity(domain: FreeLiters): FreeLitersEntity {
        return FreeLitersEntity(
            id = 1,
            fechaDesde = domain.fechaDesde,
            fechaHasta = domain.fechaHasta,
            litrosProducidos = domain.litrosProducidos,
            precioLitro = domain.precioLitro,
            costosGenerales = domain.costosGenerales,
            litrosLibres = domain.litrosLibres
        )
    }

    fun mapToDomain(entity: FreeLitersEntity): FreeLiters {
        return FreeLiters(
            fechaDesde = entity.fechaDesde,
            fechaHasta = entity.fechaHasta,
            litrosProducidos = entity.litrosProducidos,
            precioLitro = entity.precioLitro,
            costosGenerales = entity.costosGenerales,
            litrosLibres = entity.litrosLibres
        )
    }
    fun mapToEntity(domain: MarketPrice): MarketPriceEntity {
        return MarketPriceEntity(
            id = 1,
            precioLitro = domain.precioLitro,
            mes = domain.mes,
            anio = domain.anio,
            variacion = domain.variacion,
            ultimaConsulta = domain.ultimaConsulta
        )
    }

    fun mapToDomain(entity: MarketPriceEntity): MarketPrice {
        return MarketPrice(
            precioLitro = entity.precioLitro,
            mes = entity.mes,
            anio = entity.anio,
            variacion = entity.variacion,
            ultimaConsulta = entity.ultimaConsulta
        )
    }
    fun mapToEntity(domain: DashboardChart): Pair<DashboardChartHeaderEntity, List<DashboardChartPointEntity>> {
        val header = DashboardChartHeaderEntity(
            tipo = domain.tipo.key,
            hasLote = domain.hasLote
        )
        val points = domain.resultado.mapIndexed { index, point ->
            DashboardChartPointEntity(
                tipo = domain.tipo.key,
                orden = index,
                mes = point.mes,
                anio = point.anio,
                valor = point.valor
            )
        }
        return header to points
    }

    fun mapToDomain(
        tipo: DashboardChartType,
        header: DashboardChartHeaderEntity,
        points: List<DashboardChartPointEntity>
    ): DashboardChart {
        return DashboardChart(
            tipo = tipo,
            hasLote = header.hasLote,
            resultado = points.map { point ->
                DashboardChartPoint(
                    mes = point.mes,
                    anio = point.anio,
                    valor = point.valor
                )
            }
        )
    }
    fun mapToEntity(domain: LoteItem, orden: Int): LoteEntity {
        return LoteEntity(
            idLote = domain.idLote,
            orden = orden,
            numeroLote = domain.numeroLote,
            fechaProduccion = domain.fechaProduccion,
            cantidad = domain.cantidad,
            unidad = domain.unidad,
            estado = domain.estado,
            idProducto = domain.idProducto,
            idEstablecimiento = domain.idEstablecimiento,
            producto = LoteProductoEmbedded(
                idProducto = domain.producto.idProducto,
                nombre = domain.producto.nombre,
                categoria = domain.producto.categoria
            ),
            idRodeo = domain.idRodeo,
            cantAnimales = domain.cantAnimales,
            destino = domain.destino,
            tempTanque = domain.tempTanque,
            rodeo = domain.rodeo?.let {
                LoteRodeoEmbedded(
                    idRodeo = it.idRodeo,
                    tipoRodeo = it.tipoRodeo,
                    costoRacion = it.costoRacion,
                    cantVacas = it.cantVacas
                )
            },
            mermas = domain.mermas.map {
                LoteMermaRaw(
                    idMerma = it.idMerma,
                    tipo = it.tipo,
                    cantidad = it.cantidad,
                    fechaCreacion = it.fechaCreacion
                )
            },
            costosDirectos = domain.costosDirectos.map {
                LoteCostoDirectoRaw(
                    idCostoDirecto = it.idCostoDirecto,
                    concepto = it.concepto,
                    monto = it.monto,
                    observaciones = it.observaciones,
                    fechaCreacion = it.fechaCreacion
                )
            },
            produccionesIndividuales = domain.produccionesIndividuales.map {
                LoteProduccionIndividualRaw(
                    idProduccionAnimal = it.idProduccionAnimal,
                    idAnimal = it.idAnimal,
                    litros = it.litros,
                    estado = it.estado
                )
            },
            turno = domain.turno,
            mermaPorcentaje = domain.mermaPorcentaje
        )
    }

    fun mapToDomain(entity: LoteEntity): LoteItem {
        return LoteItem(
            idLote = entity.idLote,
            numeroLote = entity.numeroLote,
            fechaProduccion = entity.fechaProduccion,
            cantidad = entity.cantidad,
            unidad = entity.unidad,
            estado = entity.estado,
            idProducto = entity.idProducto,
            idEstablecimiento = entity.idEstablecimiento,
            producto = LoteProducto(
                idProducto = entity.producto.idProducto,
                nombre = entity.producto.nombre,
                categoria = entity.producto.categoria
            ),
            idRodeo = entity.idRodeo,
            cantAnimales = entity.cantAnimales,
            destino = entity.destino,
            tempTanque = entity.tempTanque,
            rodeo = entity.rodeo?.let {
                LoteRodeo(
                    idRodeo = it.idRodeo,
                    tipoRodeo = it.tipoRodeo,
                    costoRacion = it.costoRacion,
                    cantVacas = it.cantVacas
                )
            },
            mermas = entity.mermas.map {
                LoteMerma(
                    idMerma = it.idMerma,
                    tipo = it.tipo,
                    cantidad = it.cantidad,
                    fechaCreacion = it.fechaCreacion
                )
            },
            costosDirectos = entity.costosDirectos.map {
                LoteCostoDirecto(
                    idCostoDirecto = it.idCostoDirecto,
                    concepto = it.concepto,
                    monto = it.monto,
                    observaciones = it.observaciones,
                    fechaCreacion = it.fechaCreacion
                )
            },
            produccionesIndividuales = entity.produccionesIndividuales.map {
                LoteProduccionIndividual(
                    idProduccionAnimal = it.idProduccionAnimal,
                    idAnimal = it.idAnimal,
                    litros = it.litros,
                    estado = it.estado
                )
            },
            turno = entity.turno,
            mermaPorcentaje = entity.mermaPorcentaje
        )
    }
}
