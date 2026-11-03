package com.tambo360.app.feature.authentication.presentation.viewmodel.states

import com.tambo360.app.feature.authentication.domain.model.ForgotPassword

data class ForgotPasswordUiState(
    val loading: Boolean = false,
    val data: ForgotPassword? = null,
    val error: String = "",
    val resendLoading: Boolean = false,
)
