package com.tambo360.app.feature.authentication.presentation.viewmodel.states

import com.tambo360.app.feature.authentication.domain.model.VerifyResetToken

data class VerifyResetTokenUiState(
    val loading: Boolean = false,
    val data: VerifyResetToken? = null,
    val error: String = ""
)
