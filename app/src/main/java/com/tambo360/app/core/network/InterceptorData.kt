package com.tambo360.app.core.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

object InterceptorData {
    fun getInterceptorData(
        context: Context
    ): MutableList<Interceptor> {
        val interceptors: MutableList<Interceptor> = mutableListOf()
        interceptors.add(getHttpLoggingInterceptor())
        return interceptors
    }
    private fun getHttpLoggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}
