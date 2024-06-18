package com.inai.journal.presentation.auth.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.inai.journal.data.local.AuthPreferences
import com.inai.journal.data.local.DataStoreManager
import com.inai.journal.platform.GrpcClient
import com.inai.journal.ui.theme.Gray
import com.inai.journal.ui.theme.Milk
import com.inai.journal.ui.theme.Milk1
import com.inai.journal.ui.theme.Purple40
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(navController: NavHostController) {
    var username by remember { mutableStateOf(value = "") }
    var password by remember { mutableStateOf(value = "") }
    var showPassword by remember { mutableStateOf(value = false) }
    var loginError by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    val authPreferences = remember { AuthPreferences(DataStoreManager.getInstance(context)) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Box(
            contentAlignment = Alignment.BottomStart,
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
            ) {
                Text(
                    text = "Hey!",
                    style = TextStyle(
                        fontSize = 50.sp,
                        color = Color.White,
                        fontFamily = FontFamily.Monospace
                    )
                )
                Text(
                    text = "Welcome Back",
                    style = TextStyle(
                        fontSize = 50.sp,
                        color = Color.White,
                        fontFamily = FontFamily.Monospace
                    )
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 46.dp, topEnd = 46.dp))

                .background(Color.White)
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(15.dp)

            ) {
                Text(
                    text = "Login",
                    style = TextStyle(
                        fontSize = 50.sp,
                        color = Color.Black,
                        fontFamily = FontFamily.Monospace
                    )
                )
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top=60.dp)
                    ) {
                        Column (horizontalAlignment = Alignment.CenterHorizontally){
                        loginError?.let {
                            Spacer(modifier = Modifier.padding(10.dp))
                            Text(text = it, color = MaterialTheme.colors.error)
                        }
                        Spacer(modifier = Modifier.padding(10.dp))

                        OutlinedTextField(
                            value = username,
                            onValueChange = { newText -> username = newText },
                            label = { Text(text = "Username", color = Gray) },
                            singleLine = true,
                            placeholder = { Text(text = "Type username here", color = Gray) },
                            shape = RoundedCornerShape(percent = 20),
                        )

                        Spacer(modifier = Modifier.padding(10.dp))

                        OutlinedTextField(
                            value = password,
                            onValueChange = { newText -> password = newText },
                            label = { Text(text = "Password", color = Gray) },
                            placeholder = { Text(text = "Type password here", color = Gray) },
                            shape = RoundedCornerShape(percent = 20),
                            singleLine = true,
                            visualTransformation = if (showPassword) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            trailingIcon = {
                                IconButton(onClick = { showPassword = !showPassword }) {
                                    Icon(
                                        imageVector = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                        contentDescription = if (showPassword) "Hide password" else "Show password"
                                    )
                                }
                            }
                        )

                        Spacer(modifier = Modifier.padding(18.dp))

                        val grpcClient = GrpcClient(authPreferences)

                        Button(
                            onClick = {
                                grpcClient.loginStudent(username, password) { token ->
                                    if (token != null) {
                                        Log.d("LoginScreen", "$token")
                                        CoroutineScope(Dispatchers.Main).launch {
                                            navController.navigate("main") {
                                                Toast.makeText(
                                                    context,
                                                    "Login successful",
                                                    Toast.LENGTH_SHORT
                                                )
                                                    .show()
                                                popUpTo("login") { inclusive = true }
                                            }
                                        }
                                    } else {
                                        CoroutineScope(Dispatchers.Main).launch {
                                            loginError = "Incorrect username or password"
                                        }
                                    }
                                }

                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                            modifier = Modifier
                                .width(200.dp)
                                .height(60.dp)
                        ) {
                            Text(text = "Login", color = Color.White, fontSize = 20.sp)
                        }


                    }
                }
            }
        }
    }
}