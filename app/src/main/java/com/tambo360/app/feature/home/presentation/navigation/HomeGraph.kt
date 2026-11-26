package com.tambo360.app.feature.home.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.tambo360.app.feature.home.presentation.screen.HomeScreen
import com.tambo360.app.feature.navigation.HomeRoute

fun NavGraphBuilder.homeGraph(
    navController: NavHostController
) {
    composable<HomeRoute> {
        HomeScreen(navController = navController)
    }
}
