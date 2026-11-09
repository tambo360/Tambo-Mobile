package com.tambo360.app.feature.authentication.data.mapper

import com.tambo360.app.feature.authentication.data.dto.EstablecimientoResponse
import com.tambo360.app.feature.authentication.data.dto.LoginUserDataResponse
import com.tambo360.app.feature.authentication.data.dto.LoginUserResponse
import com.tambo360.app.feature.authentication.data.dto.OrganizacionResponse
import com.tambo360.app.feature.authentication.data.dto.UserResponse
import com.tambo360.app.feature.authentication.domain.model.EstablecimientoData
import com.tambo360.app.feature.authentication.domain.model.LoginUser
import com.tambo360.app.feature.authentication.domain.model.LoginUserData
import com.tambo360.app.feature.authentication.domain.model.OrganizacionData
import com.tambo360.app.feature.authentication.domain.model.User

object LoginUserMapper {

    fun LoginUserResponse.toDomain(): LoginUser {
        return LoginUser(
            success = success,
            message = message,
            data = data.toDomain()
        )
    }

    fun LoginUserDataResponse.toDomain(): LoginUserData {
        return LoginUserData(
            user = user.toDomain(),
            token = token
        )
    }

    fun UserResponse.toDomain(): User {
        return User(
            idUsuario = idUsuario,
            nombre = nombre,
            correo = correo,
            verificado = verificado,
            fechaCreacion = fechaCreacion,
            organizaciones = organizaciones.map { it.toDomain() }
        )
    }
    fun OrganizacionResponse.toDomain(): OrganizacionData {
        return OrganizacionData(
            idOrganizacionUsuario = idOrganizacionUsuario,
            idOrganizacion = idOrganizacion,
            idUsuario = idUsuario,
            rol = rol,
            estado = estado,
            fechaCreacion = fechaCreacion,
            establecimientoOrganizacionUsuarios = establecimientoOrganizacionUsuarios.map { it.toDomain() }
        )
    }
    fun EstablecimientoResponse.toDomain(): EstablecimientoData {
        return EstablecimientoData(
            idEstablecimientoOrganizacionUsuario = idEstablecimientoOrganizacionUsuario,
            idEstablecimiento = idEstablecimiento,
            idOrganizacionUsuario = idOrganizacionUsuario,
            rol = rol,
            estado = estado,
            fechaCreacion = fechaCreacion
        )
    }
}
