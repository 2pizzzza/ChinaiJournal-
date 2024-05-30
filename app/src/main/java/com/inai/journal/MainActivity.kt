package com.inai.journal

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.inai.journal.presentation.MainScreen
import com.inai.journal.ui.theme.ChinaJournalTheme
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinaJournalTheme {
//                LoginScreen()
                MainScreen()
            }
        }
    }

}
