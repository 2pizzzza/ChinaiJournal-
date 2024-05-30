package com.inai.journal.presentation.scanner

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inai.journal.R
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
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.qr_code_scanner),
                contentDescription = "",
                modifier = Modifier
                    .size(300.dp)
                )
            Button(onClick = {
                val options = ScanOptions()
                options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                options.setPrompt("Chinai Journal")
                options.setBeepEnabled(false)
                options.setBarcodeImageEnabled(true)
                scanLauncher.launch(options)
            }) {
                Text(text = "Start scan")
            }
        }


    }
}


