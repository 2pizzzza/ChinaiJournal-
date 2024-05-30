package com.inai.journal

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.inai.journal.data.local.AuthPreferences
import com.inai.journal.data.local.DataStoreManager
import com.inai.journal.presentation.MainScreen
import com.inai.journal.presentation.auth.login.LoginScreen
import com.inai.journal.presentation.profile.ProfileScreen
import com.inai.journal.ui.theme.ChinaJournalTheme
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinaJournalTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "checkAuth") {
                    composable("checkAuth") { CheckAuthScreen(navController) }
                    composable("login") { LoginScreen(navController) }
                    composable("main") { MainScreen() }
                }
            }
        }
    }
}

@Composable
fun CheckAuthScreen(navController: NavHostController) {
    val context = LocalContext.current
    val authPreferences = remember { AuthPreferences(DataStoreManager.getInstance(context)) }
    LaunchedEffect(Unit) {
        val token = runBlocking { authPreferences.getAuthToken() }
        if (token != null) {
            navController.navigate("main") {
                popUpTo("checkAuth") { inclusive = true }
            }
        } else {
            navController.navigate("login") {
                popUpTo("checkAuth") { inclusive = true }
            }
        }
    }
}