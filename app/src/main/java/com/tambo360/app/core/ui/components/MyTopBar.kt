package com.tambo360.app.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tambo360.app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    onNotificationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        ),
        title = {
            Image(
                painter = painterResource(id = R.drawable.newlogo),
                contentDescription = "",
                modifier = Modifier.height(32.dp).width(120.dp)
            )
        },
        actions = {
            IconButton(onClick = onNotificationClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notification),
                    contentDescription = "",
                    tint = Color(0xFF527602),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    )
}
