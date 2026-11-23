package com.tambo360.app.feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.tambo360.app.feature.alert.presentation.navigation.aiGraph
import com.tambo360.app.feature.costs.presentation.navigation.costsGraph
import com.tambo360.app.feature.home.presentation.navigation.homeGraph
import com.tambo360.app.feature.production.presentation.navigation.productionGraph
import com.tambo360.app.feature.setting.presentation.navigation.settingsGraph

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        modifier = modifier
    ) {
        homeGraph(navController)
        productionGraph(navController)
        costsGraph(navController)
        aiGraph(navController)
        settingsGraph(navController)
    }
}
