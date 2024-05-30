package com.inai.journal.presentation

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.inai.journal.presentation.navbar.BottomNavigationBar
import com.inai.journal.presentation.navbar.NavigationHost

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
       NavigationHost(navController = navController)
    }
}