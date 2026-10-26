package com.tambo360.app.feature.authentication.data.mapper

import com.tambo360.app.feature.authentication.data.dto.RegisterUserResponse
import com.tambo360.app.feature.authentication.domain.model.RegisterUser

object RegisterUserMapper {
    fun RegisterUserResponse.toDomain(): RegisterUser {
        return RegisterUser(
            idUsuario = data.idUsuario,
            nombre = data.nombre,
            correo = data.correo,
            verificado = data.verificado,
            fechaCreacion = data.fechaCreacion
        )
    }
}
