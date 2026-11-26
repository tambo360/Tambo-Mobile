package com.tambo360.app.feature.home.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.tambo360.app.core.ui.components.MyTopBar
import com.tambo360.app.feature.home.presentation.content.HomeContent

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopBar(onNotificationClick = {})
        }
    ) { innerPadding ->
        HomeContent(
            modifier = Modifier.padding(innerPadding),
            onSeeAllClick = {

            }
        )
    }
}
