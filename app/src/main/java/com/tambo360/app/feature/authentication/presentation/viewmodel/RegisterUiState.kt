package com.tambo360.app.feature.authentication.presentation.viewmodel

import com.tambo360.app.feature.authentication.domain.model.RegisterUser

data class RegisterUiState(
    val loading: Boolean = false,
    val data: RegisterUser? = null,
    val error: String = ""
)
