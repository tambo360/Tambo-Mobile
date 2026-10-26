package com.tambo360.app.feature.authentication.domain.model

data class RegisterUser(
    val success: Boolean,
    val message: String,
    val data: RegisterUserData
)
