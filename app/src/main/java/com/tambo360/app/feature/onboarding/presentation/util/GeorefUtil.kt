package com.tambo360.app.feature.onboarding.presentation.util

import com.tambo360.app.feature.onboarding.data.remote.GeorefApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class Provincia(val id: String, val nombre: String)

data class LocalidadDto(val id: String, val nombre: String)

data class GeorefResponse(val localidades: List<LocalidadDto>)

object ArgentinaProvincias {
    val lista = listOf(
        Provincia("06", "Buenos Aires"),
        Provincia("02", "CABA"),
        Provincia("10", "Catamarca"),
        Provincia("22", "Chaco"),
        Provincia("26", "Chubut"),
        Provincia("14", "Córdoba"),
        Provincia("18", "Corrientes"),
        Provincia("30", "Entre Ríos"),
        Provincia("34", "Formosa"),
        Provincia("38", "Jujuy"),
        Provincia("42", "La Pampa"),
        Provincia("46", "La Rioja"),
        Provincia("50", "Mendoza"),
        Provincia("54", "Misiones"),
        Provincia("58", "Neuquén"),
        Provincia("62", "Río Negro"),
        Provincia("66", "Salta"),
        Provincia("70", "San Juan"),
        Provincia("74", "San Luis"),
        Provincia("78", "Santa Cruz"),
        Provincia("82", "Santa Fe"),
        Provincia("86", "Santiago del Estero"),
        Provincia("94", "Tierra del Fuego"),
        Provincia("19", "Tucumán")
    )
}

object GeorefProvider {
    private val api: GeorefApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://apis.datos.gob.ar/georef/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GeorefApi::class.java)
    }

    suspend fun fetchLocalidades(idProvincia: String): Result<List<LocalidadDto>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getLocalidades(idProvincia)
                Result.success(response.localidades)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
