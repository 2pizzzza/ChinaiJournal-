package com.inai.journal.presentation.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.inai.journal.R
import com.inai.journal.ui.theme.Milk
import com.inai.journal.ui.theme.Milk1
import com.inai.journal.ui.theme.Purple40

@Preview(showSystemUi = true)
@Composable
fun NotificationScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Notifications",
                style = TextStyle(
                    fontSize = 30.sp,
                    color = Color.Black
                )
            )
            Column(modifier = Modifier.fillMaxWidth()
                .padding(top = 130.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anime9))
                val isPlaying by remember { mutableStateOf(true) }
                val progress by animateLottieCompositionAsState(
                    composition = composition,
                    isPlaying = isPlaying )
                LottieAnimation(
                    composition = composition,
                    modifier = Modifier
                        .size(350.dp),
                    iterations = LottieConstants.IterateForever,
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = "You don't have notification",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W200
                    )
                )
            }
        }
    }
}