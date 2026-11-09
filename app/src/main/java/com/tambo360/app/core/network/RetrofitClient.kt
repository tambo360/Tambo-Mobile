package com.tambo360.app.core.network

import okhttp3.CookieJar
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.collections.forEach

object RetrofitClient {
    private var retrofit: Retrofit? = null

    fun getClient(interceptor: MutableList<Interceptor>, cookieJar: CookieJar): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .client(getOkHttpLog(interceptor, cookieJar))
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ServiceConstants.BASE_URL)
                .build()
        }
        return retrofit
    }

    private fun getOkHttpLog(
        interceptor: MutableList<Interceptor>,
        cookieJar: CookieJar
    ): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
            .cookieJar(cookieJar)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

        interceptor.forEach {
            okHttpBuilder.addInterceptor(it)
        }

        return okHttpBuilder.build()
    }
}
