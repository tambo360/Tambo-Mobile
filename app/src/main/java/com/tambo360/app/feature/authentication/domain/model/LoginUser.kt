package com.tambo360.app.feature.authentication.domain.model

data class LoginUser(
    val success: Boolean,
    val message: String,
    val data: LoginUserData
)
