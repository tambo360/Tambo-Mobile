package com.tambo360.app.feature.production.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.tambo360.app.feature.production.presentation.components.ProductionTopBar
import com.tambo360.app.feature.production.presentation.content.ProductionContent

@Composable
fun ProductionScreen(
    navController: NavHostController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ProductionTopBar(
                name = "RM",
                onFilterClick = {

                }
            )
        }
    ) { innerPadding ->
        ProductionContent(
            modifier = Modifier.padding(innerPadding),
            onRegisterLotClick = {

            }
        )
    }
}
