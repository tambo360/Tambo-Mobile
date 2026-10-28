package com.tambo360.app.feature.authentication.domain.model

data class RegisterUserData(
    val idUsuario: String,
    val nombre: String,
    val correo: String,
    val verificado: Boolean,
    val fechaCreacion: String
)
