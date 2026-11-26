package com.tambo360.app.feature.home.di

import com.tambo360.app.core.database.AppDatabase
import com.tambo360.app.feature.home.data.local.dao.HomeDao
import com.tambo360.app.feature.home.data.remote.HomeApi
import com.tambo360.app.feature.home.data.repository.HomeRepositoryImpl
import com.tambo360.app.feature.home.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeApi(retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

    @Provides
    fun providesHomeDao(db: AppDatabase): HomeDao {
        return db.homeDao()
    }

    @Provides
    @Singleton
    fun provideHomeRepository(
        homeApi: HomeApi,
        dao: HomeDao
    ): HomeRepository {
        return HomeRepositoryImpl(homeApi, dao)
    }
}
