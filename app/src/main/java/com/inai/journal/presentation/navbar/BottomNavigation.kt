package com.inai.journal.presentation.navbar

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Scanner,
        BottomNavItem.Notification,
        BottomNavItem.Profile
    )

    BottomNavigation(
        contentColor = Color.White,
        backgroundColor = Color.White,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            val isSelected = currentRoute == item.route

            val scale by animateFloatAsState(
                targetValue = if (isSelected) 1.2f else 1.0f,
                animationSpec = tween(
                    durationMillis = 900,
                    easing = LinearOutSlowInEasing
                )
            )
            val elevation by animateDpAsState(
                targetValue = if (isSelected) 8.dp else 0.dp,
                animationSpec = tween(
                    durationMillis = 900,
                    easing = LinearOutSlowInEasing
                )
            )
            val alpha by animateFloatAsState(
                targetValue = if (isSelected) 1.0f else 0.0f,
                animationSpec = tween(
                    durationMillis = 900,
                    easing = LinearOutSlowInEasing
                )
            )

            BottomNavigationItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.White.copy(0.5f),
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .scale(scale)
                            .padding(top = elevation)
                    ) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null
                        )
                        if (isSelected) {
                            Text(
                                text = item.title,
                                modifier = Modifier.alpha(alpha)
                            )
                        }
                    }
                },
            )
        }
    }
}


