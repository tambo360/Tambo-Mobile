package com.tambo360.app.feature.authentication.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tambo360.app.feature.authentication.presentation.content.ChangePasswordContent
import com.tambo360.app.feature.authentication.presentation.content.UpdatePasswordSuccess
import com.tambo360.app.feature.authentication.presentation.viewmodel.ResetPasswordViewModel

@Composable
fun ChangePasswordScreen(
    token: String?,
    goToLogin: () -> Unit,
    viewModel: ResetPasswordViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    if (state.data?.success == true) {
        UpdatePasswordSuccess(goToLogin = goToLogin)
    } else {
        ChangePasswordContent(
            state = state,
            goToLogin = goToLogin,
            onUpdatePassword = { new ->
                token?.let { tk -> viewModel.resetPassword(token = tk, newPassword = new) }
            }
        )
    }
}
