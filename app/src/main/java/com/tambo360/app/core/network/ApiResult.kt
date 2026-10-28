package com.tambo360.app.core.network

import com.google.gson.JsonSyntaxException
import retrofit2.Response
import java.io.IOException

sealed class NetworkResult<out t> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Failed(val message: String) : NetworkResult<Nothing>()
}

suspend fun <T> safeApiCall(call: suspend () -> Response<T>): NetworkResult<T> {
    return try {
        val response = call()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                NetworkResult.Success(body)
            } else {
                NetworkResult.Failed("Respuesta vacía del servidor")
            }
        } else {
            val errorMensaje = try {
                response.errorBody()?.string()?.takeIf { it.isNotBlank() }
                    ?: "Error ${response.code()}"
            } catch (e: Exception) {
                "Error ${response.code()}"
            }
            NetworkResult.Failed(errorMensaje)
        }
    } catch (e: IOException) {
        NetworkResult.Failed("Error de conexión: ${e.localizedMessage ?: "Verifica tu internet"}")
    } catch (e: JsonSyntaxException) {
        NetworkResult.Failed("Error al procesar los datos $e")
    } catch (e: Exception) {
        NetworkResult.Failed("Error desconocido: ${e.localizedMessage ?: "Ocurrió un error inesperado"}")
    }
}
