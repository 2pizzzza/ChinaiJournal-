package com.inai.journal.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.inai.journal.MainActivity
import com.inai.journal.R
import com.inai.journal.ui.theme.ChinaJournalTheme
import kotlinx.coroutines.delay

@Suppress("DEPRECATION")
@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinaJournalTheme {
                SplashScreen()
            }
        }

    }

    @Preview(showSystemUi = true)
    @Composable
    private fun SplashScreen() {
        LaunchedEffect(key1 = true) {
            delay(2000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anime5))
            val isPlaying by remember { mutableStateOf(true) }
            val progress by animateLottieCompositionAsState(
                composition = composition,
                isPlaying = isPlaying
            )
            LottieAnimation(
                composition = composition,
                modifier = Modifier
                    .size(500.dp),
                progress = { progress }
            )
        }
    }
}