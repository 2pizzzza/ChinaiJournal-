package com.inai.journal.presentation.auth.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.inai.journal.data.local.AuthPreferences
import com.inai.journal.data.local.DataStoreManager
import com.inai.journal.platform.GrpcClient
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
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "INAI",
                style = TextStyle(fontSize = 50.sp, color = Purple40)
            )

            Spacer(modifier = Modifier.padding(10.dp))
            loginError?.let {
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = it, color = MaterialTheme.colors.error)
            }
            Spacer(modifier = Modifier.padding(10.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { newText -> username = newText },
                label = { Text(text = "Username") },
                singleLine = true,
                placeholder = { Text(text = "Type username here") },
                shape = RoundedCornerShape(percent = 20),
            )

            Spacer(modifier = Modifier.padding(10.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { newText -> password = newText },
                label = { Text(text = "Password") },
                placeholder = { Text(text = "Type password here") },
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

            Spacer(modifier = Modifier.padding(10.dp))

            val grpcClient = GrpcClient(authPreferences)

            Button(
                onClick = {
                    grpcClient.loginStudent(username, password) { token ->
                        if (token != null) {
                            Log.d("LoginScreen", "$token")
                            CoroutineScope(Dispatchers.Main).launch {
                            navController.navigate("main") {
                                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                                popUpTo("login") { inclusive = true }
                            }}
                        } else {
                            CoroutineScope(Dispatchers.Main).launch {
                                loginError = "Incorrect username or password"
                            }
                        }
                    }

                },
                modifier = Modifier.width(150.dp)
            ) {
                Text(text = "Login")
            }


        }
    }
}