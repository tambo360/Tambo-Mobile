package com.tambo360.app.feature.onboarding.di

import com.tambo360.app.feature.onboarding.data.remote.OnboardingApi
import com.tambo360.app.feature.onboarding.data.repository.OnboardingRepositoryImpl
import com.tambo360.app.feature.onboarding.domain.repository.OnboardingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OnboardingModule {

    @Provides
    @Singleton
    fun provideOnboardingApi(retrofit: Retrofit): OnboardingApi {
        return retrofit.create(OnboardingApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOnboardingRepository(
        onboardingApi: OnboardingApi
    ): OnboardingRepository {
        return OnboardingRepositoryImpl(onboardingApi)
    }
}
