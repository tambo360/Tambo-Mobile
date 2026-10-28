package com.tambo360.app.feature.authentication.data.mapper

import com.tambo360.app.feature.authentication.data.dto.RegisterUserDataResponse
import com.tambo360.app.feature.authentication.data.dto.RegisterUserResponse
import com.tambo360.app.feature.authentication.domain.model.RegisterUser
import com.tambo360.app.feature.authentication.domain.model.RegisterUserData

object RegisterUserMapper {
    fun RegisterUserResponse.toDomain(): RegisterUser {
        return RegisterUser(
            success = success,
            message = message,
            data = data.toDomain()
        )
    }

    fun RegisterUserDataResponse.toDomain(): RegisterUserData {
        return RegisterUserData(
            idUsuario = idUsuario,
            nombre = nombre,
            correo = correo,
            verificado = verificado,
            fechaCreacion = fechaCreacion
        )
    }
}
