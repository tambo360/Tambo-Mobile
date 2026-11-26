package com.tambo360.app.feature.home.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.tambo360.app.feature.home.data.local.entity.CurrentMonthSummaryEntity
import com.tambo360.app.feature.home.data.local.entity.DashboardChartHeaderEntity
import com.tambo360.app.feature.home.data.local.entity.DashboardChartPointEntity
import com.tambo360.app.feature.home.data.local.entity.FreeLitersEntity
import com.tambo360.app.feature.home.data.local.entity.GeneralCostItemEntity
import com.tambo360.app.feature.home.data.local.entity.GeneralCostsEntity
import com.tambo360.app.feature.home.data.local.entity.LoteEntity
import com.tambo360.app.feature.home.data.local.entity.MarketPriceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentMonthSummary(summary: CurrentMonthSummaryEntity)
    @Query("SELECT * FROM current_month_summary WHERE id = 1")
    fun getCurrentMonthSummary(): Flow<CurrentMonthSummaryEntity?>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGeneralCostsHeader(header: GeneralCostsEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGeneralCostItems(items: List<GeneralCostItemEntity>)
    @Query("DELETE FROM general_cost_items")
    suspend fun clearGeneralCostItems()
    @Transaction
    suspend fun replaceGeneralCosts(
        header: GeneralCostsEntity,
        items: List<GeneralCostItemEntity>
    ) {
        clearGeneralCostItems()
        insertGeneralCostsHeader(header)
        insertGeneralCostItems(items)
    }
    @Query("SELECT * FROM general_costs WHERE id = 1")
    fun getGeneralCostsHeader(): Flow<GeneralCostsEntity?>
    @Query("SELECT * FROM general_cost_items ORDER BY clave ASC")
    fun getGeneralCostItems(): Flow<List<GeneralCostItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFreeLiters(freeLiters: FreeLitersEntity)

    @Query("SELECT * FROM free_liters WHERE id = 1")
    fun getFreeLiters(): Flow<FreeLitersEntity?>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMarketPrice(marketPrice: MarketPriceEntity)
    @Query("SELECT * FROM market_price WHERE id = 1")
    fun getMarketPrice(): Flow<MarketPriceEntity?>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDashboardChartHeader(header: DashboardChartHeaderEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDashboardChartPoints(points: List<DashboardChartPointEntity>)
    @Query("DELETE FROM dashboard_chart_points WHERE tipo = :tipo")
    suspend fun clearDashboardChartPointsByType(tipo: String)
    @Transaction
    suspend fun replaceDashboardChart(
        header: DashboardChartHeaderEntity,
        points: List<DashboardChartPointEntity>
    ) {
        clearDashboardChartPointsByType(header.tipo)
        insertDashboardChartHeader(header)
        insertDashboardChartPoints(points)
    }
    @Query("SELECT * FROM dashboard_chart_header WHERE tipo = :tipo")
    fun getDashboardChartHeader(tipo: String): Flow<DashboardChartHeaderEntity?>
    @Query("SELECT * FROM dashboard_chart_points WHERE tipo = :tipo ORDER BY orden ASC")
    fun getDashboardChartPoints(tipo: String): Flow<List<DashboardChartPointEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLotes(lotes: List<LoteEntity>)
    @Query("DELETE FROM home_lotes")
    suspend fun clearLotes()
    @Transaction
    suspend fun replaceLotes(lotes: List<LoteEntity>) {
        clearLotes()
        insertLotes(lotes)
    }
    @Query("SELECT * FROM home_lotes ORDER BY orden ASC")
    fun getLotes(): Flow<List<LoteEntity>>
}
