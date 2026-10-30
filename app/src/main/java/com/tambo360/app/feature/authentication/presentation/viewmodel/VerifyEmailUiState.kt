package com.tambo360.app.feature.authentication.presentation.viewmodel

import com.tambo360.app.feature.authentication.domain.model.ResendVerification
import com.tambo360.app.feature.authentication.domain.model.VerifyEmail

data class VerifyEmailUiState(
    val loading: Boolean = false,
    val data: VerifyEmail? = null,
    val error: String = "",
    val resendLoading: Boolean = false,
    val resendData: ResendVerification? = null,
    val resendError: String = ""
)
