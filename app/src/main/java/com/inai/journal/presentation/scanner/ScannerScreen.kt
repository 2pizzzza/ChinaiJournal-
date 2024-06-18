@file:Suppress("DEPRECATION")

package com.inai.journal.presentation.scanner

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.inai.journal.R
import com.inai.journal.presentation.home.frelist
import com.inai.journal.ui.theme.Milk
import com.inai.journal.ui.theme.Milk1
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions


@Preview(showSystemUi = true)
@Composable
fun ScannerScreen(){
    val qrcode = remember {
        mutableStateOf("Scanner")
    }
    val context = LocalContext.current
    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = { result ->
            if (result.contents == null) {
            } else {
                qrcode.value = result.contents
                Toast.makeText(context, "Содержимое: ${result.contents}", Toast.LENGTH_SHORT).show()
            }
        }
    )
    Box(modifier = Modifier.fillMaxSize().background(Color.White), contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .padding(bottom = 70.dp)) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anime7))
            val isPlaying by remember { mutableStateOf(true) }
            val progress by animateLottieCompositionAsState(
                composition = composition,
                isPlaying = isPlaying )
            LottieAnimation(
                composition = composition,
                modifier = Modifier
                    .size(350.dp)
                    .padding(start = 60.dp),
                iterations = LottieConstants.IterateForever,
            )
            Button(onClick = {
                val options = ScanOptions()
                options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                options.setPrompt("Chinai Journal")
                options.setBeepEnabled(false)
                options.setBarcodeImageEnabled(true)
                scanLauncher.launch(options)
            },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp)

            ) {
                Text(text = "Start scan", color = Color.White)
            }
        }
        when(qrcode.value){
            "11"-> frelist[0].isHear = true
            "12"-> frelist[1].isHear = true
            "13"-> frelist[2].isHear = true
        }


    }
}


