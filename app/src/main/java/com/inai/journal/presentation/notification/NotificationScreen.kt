package com.inai.journal.presentation.notification

import androidx.compose.foundation.Image
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
import com.inai.journal.R
import com.inai.journal.ui.theme.Purple40

@Preview(showSystemUi = true)
@Composable
fun NotificationScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp),
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Benachrichtigung",
                style = TextStyle(
                    fontSize = 30.sp,
                    color = Purple40
                    )
            )
            Spacer(modifier = Modifier.padding(50.dp))
            Image(
                painter = painterResource(id = R.drawable.notification_img),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(300.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = "Sie haben keine Benachrichtigungen",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W200
                )
            )
        }
    }
}