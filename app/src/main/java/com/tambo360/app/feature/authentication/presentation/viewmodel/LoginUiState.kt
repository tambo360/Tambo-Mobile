package com.tambo360.app.feature.authentication.presentation.viewmodel

import com.tambo360.app.feature.authentication.domain.model.LoginUser

data class LoginUiState(
    val loading: Boolean = false,
    val data: LoginUser? = null,
    val error: String = ""
)
