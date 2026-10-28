package com.tambo360.app.feature.authentication.domain.model

data class LoginUserData(
    val user: User,
    val token: String
)
