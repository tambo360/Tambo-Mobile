package com.tambo360.app.feature.authentication.domain.model

data class VerifyEmail(
    val success: Boolean,
    val message: String,
    val data: VerifyEmailData
)
