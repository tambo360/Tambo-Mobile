package com.tambo360.app.core.di

import android.content.Context
import com.tambo360.app.core.data.TokenRepository
import com.tambo360.app.core.network.InterceptorData
import com.tambo360.app.core.network.RetrofitClient
import com.tambo360.app.core.network.TokenCookieJar
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.CookieJar
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideCookieJar(tokenRepository: TokenRepository): CookieJar {
        return TokenCookieJar(tokenRepository)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        @ApplicationContext context: Context,
        cookieJar: CookieJar
    ): Retrofit {
        return RetrofitClient.getClient(
            interceptor = InterceptorData.getInterceptorData(context),
            cookieJar = cookieJar
        )!!
    }
}
