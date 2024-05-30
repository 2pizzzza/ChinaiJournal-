package com.inai.journal.presentation.navbar

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.inai.journal.presentation.home.HomeScreen
import com.inai.journal.presentation.notification.NotificationScreen
import com.inai.journal.presentation.profile.ProfileScreen
import com.inai.journal.presentation.scanner.ScannerScreen

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) { HomeScreen() }
        composable(BottomNavItem.Scanner.route) { ScannerScreen()}
        composable(BottomNavItem.Notification.route) { NotificationScreen() }
        composable(BottomNavItem.Profile.route) { ProfileScreen() }
    }
}