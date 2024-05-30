package com.inai.journal.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inai.journal.R

@Preview(showSystemUi = true)
@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.account),
                contentDescription = "profileImg",
                modifier = Modifier
                    .size(200.dp)
            )
            Row {
                Text(
                    text = "First ",
                    style = TextStyle(fontSize = 25.sp)
                )
                Text(
                    text = "Last",
                    style = TextStyle(fontSize = 25.sp)
                )
            }
            Text(
                text = "Min 1 22",
                style = TextStyle(fontSize = 15.sp)
                )

            Spacer(modifier = Modifier.padding(10.dp))

            Button(
                onClick = { /*TODO*/ }) {
                Text(text = "Logout")
            }
        }
    }
}