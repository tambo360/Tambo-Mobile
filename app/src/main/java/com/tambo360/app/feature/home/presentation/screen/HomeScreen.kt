package com.tambo360.app.feature.home.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.tambo360.app.core.ui.components.MyTopBar
import com.tambo360.app.feature.home.presentation.content.HomeContent
import com.tambo360.app.feature.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopBar(onNotificationClick = {})
        }
    ) { innerPadding ->
        HomeContent(
            uiState = uiState,
            modifier = Modifier.padding(innerPadding),
            onSeeAllClick = {

            }
        )
    }
}
