package com.tambo360.app.core.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.tambo360.app.core.ui.viewmodel.StartScreenViewModel
import com.tambo360.app.core.ui.viewmodel.UiState
import com.tambo360.app.feature.authentication.presentation.content.RegisterContent
import com.tambo360.app.feature.authentication.presentation.screen.RegisterScreen

@Composable
fun AppScreen(
    viewModel: StartScreenViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    when (state) {
        UiState.Register -> {
            RegisterScreen(
                goTo = {
                    viewModel.updateUiState(state = UiState.Home)
                }
            )
        }

        UiState.Home -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "REVISA EMAIL",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
