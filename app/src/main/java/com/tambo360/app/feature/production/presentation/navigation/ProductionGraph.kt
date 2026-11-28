package com.tambo360.app.feature.production.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.tambo360.app.feature.navigation.ProductionRoute
import com.tambo360.app.feature.production.presentation.screen.ProductionScreen

fun NavGraphBuilder.productionGraph(
    navController: NavHostController
) {
    composable<ProductionRoute> {
        ProductionScreen(navController = navController)
    }
}
