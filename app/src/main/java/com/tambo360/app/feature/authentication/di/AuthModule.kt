package com.tambo360.app.feature.authentication.di

import android.content.Context
import android.content.SharedPreferences
import com.tambo360.app.feature.authentication.data.remote.AuthApi
import com.tambo360.app.feature.authentication.data.repository.AuthRepositoryImpl
import com.tambo360.app.feature.authentication.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    private const val AUTH_PREF_NAME = "auth_prefs"

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(AUTH_PREF_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        authApi: AuthApi,
        sharedPreferences: SharedPreferences
    ): AuthRepository {
        return AuthRepositoryImpl(authApi, sharedPreferences)
    }
}
