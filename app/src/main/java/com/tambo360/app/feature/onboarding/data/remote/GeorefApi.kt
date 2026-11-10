package com.tambo360.app.feature.onboarding.data.remote

import com.tambo360.app.feature.onboarding.presentation.util.GeorefResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GeorefApi {

    @GET("localidades")
    suspend fun getLocalidades(
        @Query("provincia") id: String,
        @Query("max") max: Int = 200,
        @Query("campos") campos: String = "id,nombre"
    ): GeorefResponse

}
