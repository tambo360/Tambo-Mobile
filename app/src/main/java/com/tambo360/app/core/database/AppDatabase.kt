package com.tambo360.app.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tambo360.app.feature.home.data.local.RoomConverters
import com.tambo360.app.feature.home.data.local.entity.CurrentMonthSummaryEntity
import com.tambo360.app.feature.home.data.local.dao.HomeDao
import com.tambo360.app.feature.home.data.local.entity.DashboardChartHeaderEntity
import com.tambo360.app.feature.home.data.local.entity.DashboardChartPointEntity
import com.tambo360.app.feature.home.data.local.entity.FreeLitersEntity
import com.tambo360.app.feature.home.data.local.entity.GeneralCostItemEntity
import com.tambo360.app.feature.home.data.local.entity.GeneralCostsEntity
import com.tambo360.app.feature.home.data.local.entity.LoteEntity
import com.tambo360.app.feature.home.data.local.entity.MarketPriceEntity

@Database(
    entities = [
        CurrentMonthSummaryEntity::class,
        GeneralCostsEntity::class,
        GeneralCostItemEntity::class,
        FreeLitersEntity::class,
        MarketPriceEntity::class,
        DashboardChartHeaderEntity::class,
        DashboardChartPointEntity::class,
        LoteEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun homeDao(): HomeDao
}
