package com.tambo360.app.feature.home.data.repository

import com.tambo360.app.core.network.NetworkResult
import com.tambo360.app.core.network.safeApiCall
import com.tambo360.app.feature.home.data.local.dao.HomeDao
import com.tambo360.app.feature.home.data.mapper.CurrentMonthSummaryMapper.toDomain
import com.tambo360.app.feature.home.data.mapper.DashboardChartMapper.toDomain
import com.tambo360.app.feature.home.data.mapper.DashboardChartType
import com.tambo360.app.feature.home.data.mapper.FreeLitersMapper.toDomain
import com.tambo360.app.feature.home.data.mapper.GeneralCostsMapper.toDomain
import com.tambo360.app.feature.home.data.mapper.HomeDataMapper
import com.tambo360.app.feature.home.data.mapper.LotesMapper.toDomain
import com.tambo360.app.feature.home.data.mapper.MarketPriceMapper.toDomain
import com.tambo360.app.feature.home.data.remote.HomeApi
import com.tambo360.app.feature.home.domain.model.CurrentMonthSummary
import com.tambo360.app.feature.home.domain.model.DashboardChart
import com.tambo360.app.feature.home.domain.model.FreeLiters
import com.tambo360.app.feature.home.domain.model.GeneralCosts
import com.tambo360.app.feature.home.domain.model.LoteItem
import com.tambo360.app.feature.home.domain.model.MarketPrice
import com.tambo360.app.feature.home.domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeApi: HomeApi,
    private val dao: HomeDao
) : HomeRepository {

    override suspend fun syncCurrentMonthSummary(
        idOrganizacion: String,
        idEstablecimiento: String
    ): NetworkResult<CurrentMonthSummary> {
        return withContext(Dispatchers.IO) {
            val result = safeApiCall {
                homeApi.getCurrentMonthSummary(
                    idOrganizacion = idOrganizacion,
                    idEstablecimiento = idEstablecimiento
                )
            }
            when (result) {
                is NetworkResult.Success -> {
                    val domain = result.data.toDomain()
                    dao.insertCurrentMonthSummary(HomeDataMapper.mapToEntity(domain))
                    NetworkResult.Success(domain)
                }

                is NetworkResult.Failed -> {
                    NetworkResult.Failed(result.message)
                }
            }
        }
    }

    override fun getCurrentMonthSummary(): Flow<CurrentMonthSummary?> {
        return dao.getCurrentMonthSummary().map { entity ->
            entity?.let(HomeDataMapper::mapToDomain)
        }
    }

    override suspend fun syncGeneralCosts(
        idOrganizacion: String,
        idEstablecimiento: String
    ): NetworkResult<GeneralCosts> {
        return withContext(Dispatchers.IO) {
            val result = safeApiCall {
                homeApi.getGeneralCosts(
                    idOrganizacion = idOrganizacion,
                    idEstablecimiento = idEstablecimiento
                )
            }
            when (result) {
                is NetworkResult.Success -> {
                    val domain = result.data.toDomain()
                    val (header, items) = HomeDataMapper.mapToEntity(domain)
                    dao.replaceGeneralCosts(header, items)
                    NetworkResult.Success(domain)
                }

                is NetworkResult.Failed -> {
                    NetworkResult.Failed(result.message)
                }
            }
        }
    }

    override fun getGeneralCosts(): Flow<GeneralCosts?> {
        return combine(
            dao.getGeneralCostsHeader(),
            dao.getGeneralCostItems()
        ) { header, items ->
            header?.let { HomeDataMapper.mapToDomain(it, items) }
        }
    }

    override suspend fun syncFreeLiters(
        idOrganizacion: String,
        idEstablecimiento: String
    ): NetworkResult<FreeLiters> {
        return withContext(Dispatchers.IO) {
            val result = safeApiCall {
                homeApi.getFreeLiters(
                    idOrganizacion = idOrganizacion,
                    idEstablecimiento = idEstablecimiento
                )
            }
            when (result) {
                is NetworkResult.Success -> {
                    val domain = result.data.toDomain()
                    dao.insertFreeLiters(HomeDataMapper.mapToEntity(domain))
                    NetworkResult.Success(domain)
                }

                is NetworkResult.Failed -> {
                    NetworkResult.Failed(result.message)
                }
            }
        }
    }

    override fun getFreeLiters(): Flow<FreeLiters?> {
        return dao.getFreeLiters().map { entity ->
            entity?.let(HomeDataMapper::mapToDomain)
        }
    }

    override suspend fun syncMarketPrice(
        idOrganizacion: String,
        idEstablecimiento: String
    ): NetworkResult<MarketPrice> {
        return withContext(Dispatchers.IO) {
            val result = safeApiCall {
                homeApi.getMarketPrice(
                    idOrganizacion = idOrganizacion,
                    idEstablecimiento = idEstablecimiento
                )
            }
            when (result) {
                is NetworkResult.Success -> {
                    val domain = result.data.toDomain()
                    dao.insertMarketPrice(HomeDataMapper.mapToEntity(domain))
                    NetworkResult.Success(domain)
                }

                is NetworkResult.Failed -> {
                    NetworkResult.Failed(result.message)
                }
            }
        }
    }

    override fun getMarketPrice(): Flow<MarketPrice?> {
        return dao.getMarketPrice().map { entity ->
            entity?.let(HomeDataMapper::mapToDomain)
        }
    }

    override suspend fun syncDashboardChart(
        idOrganizacion: String,
        idEstablecimiento: String,
        tipo: DashboardChartType
    ): NetworkResult<DashboardChart> {
        return withContext(Dispatchers.IO) {
            val result = safeApiCall {
                homeApi.getDashboardChart(
                    idOrganizacion = idOrganizacion,
                    idEstablecimiento = idEstablecimiento,
                    producto = tipo.producto,
                    metrica = tipo.metrica
                )
            }
            when (result) {
                is NetworkResult.Success -> {
                    val domain = result.data.toDomain(tipo)
                    val (header, points) = HomeDataMapper.mapToEntity(domain)
                    dao.replaceDashboardChart(header, points)
                    NetworkResult.Success(domain)
                }

                is NetworkResult.Failed -> {
                    NetworkResult.Failed(result.message)
                }
            }
        }
    }

    override fun getDashboardChart(tipo: DashboardChartType): Flow<DashboardChart?> {
        return combine(
            dao.getDashboardChartHeader(tipo.key),
            dao.getDashboardChartPoints(tipo.key)
        ) { header, points ->
            header?.let { HomeDataMapper.mapToDomain(tipo, it, points) }
        }
    }

    override suspend fun syncLotes(
        idOrganizacion: String,
        idEstablecimiento: String,
        page: Int?,
        limit: Int?,
        orden: String?,
        estado: Boolean?,
        nombre: String?,
        producto: String?,
        numeroLote: Int?,
        fechaDesde: String?,
        fechaHasta: String?
    ): NetworkResult<List<LoteItem>> {
        return withContext(Dispatchers.IO) {
            val result = safeApiCall {
                homeApi.getLotes(
                    idOrganizacion = idOrganizacion,
                    idEstablecimiento = idEstablecimiento,
                    page = page,
                    limit = limit,
                    orden = orden,
                    estado = estado,
                    nombre = nombre,
                    producto = producto,
                    numeroLote = numeroLote,
                    fechaDesde = fechaDesde,
                    fechaHasta = fechaHasta
                )
            }
            when (result) {
                is NetworkResult.Success -> {
                    val domainList = result.data.toDomain()
                    val entities = domainList.mapIndexed { index, lote ->
                        HomeDataMapper.mapToEntity(lote, orden = index)
                    }
                    dao.replaceLotes(entities)
                    NetworkResult.Success(domainList)
                }

                is NetworkResult.Failed -> {
                    NetworkResult.Failed(result.message)
                }
            }
        }
    }

    override fun getLotes(): Flow<List<LoteItem>> {
        return dao.getLotes().map { entities ->
            entities.map(HomeDataMapper::mapToDomain)
        }
    }
}
