package com.tambo360.app.feature.authentication.presentation.viewmodel.states

import com.tambo360.app.feature.authentication.domain.model.ResetPassword

data class ResetPasswordUiState(
    val loading: Boolean = false,
    val data: ResetPassword? = null,
    val error: String = ""
)
