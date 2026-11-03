package com.tambo360.app.feature.authentication.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tambo360.app.feature.authentication.presentation.content.ForgotCheckContent
import com.tambo360.app.feature.authentication.presentation.viewmodel.ForgotPasswordViewModel
import com.tambo360.app.feature.authentication.presentation.viewmodel.VerifyResetTokenViewModel

@Composable
fun ForgotCheckScreen(
    token: String?,
    email: String,
    onChangeEmail: () -> Unit,
    goTo: () -> Unit,
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    viewModelVerify: VerifyResetTokenViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val verifyState by viewModelVerify.uiState.collectAsStateWithLifecycle()
    val timerState by viewModel.timerState.collectAsStateWithLifecycle()

    LaunchedEffect(token) {
        if (!token.isNullOrEmpty()) {
            viewModelVerify.verifyResetToken(token = token)
        }
    }

    LaunchedEffect(verifyState) {
        verifyState.data?.let {
            if (it.success) {
                goTo()
            }
        }
    }

    ForgotCheckContent(
        email = email,
        state = state,
        timerState = timerState,
        onChangeEmail = onChangeEmail,
        onResendClick = {
            viewModel.resendForgot(email = email)
        }
    )
}
