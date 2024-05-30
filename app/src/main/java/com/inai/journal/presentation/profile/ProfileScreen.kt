package com.inai.journal.presentation.profile

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import auth.GetStudentProfileDataResponse
import com.inai.journal.MainActivity
import com.inai.journal.R
import com.inai.journal.data.local.AuthPreferences
import com.inai.journal.data.local.DataStoreManager
import com.inai.journal.platform.GrpcClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ProfileScreen( ) {
    var profileState by remember { mutableStateOf<ProfileState>(ProfileState.Loading) }
    val context = LocalContext.current
    val authPreferences = remember { AuthPreferences(DataStoreManager.getInstance(context)) }
    val token = remember {
        mutableStateOf("")
    }

    val grpcClient = GrpcClient(authPreferences)
    LaunchedEffect(Unit) {
        grpcClient.getStudentProfileData { response ->
            profileState = if (response != null) {
                ProfileState.Loaded(response)
            } else {
                ProfileState.Error("Failed to load profile data")
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val state = profileState) {
            is ProfileState.Loading -> CircularProgressIndicator()
            is ProfileState.Loaded -> {
                val profile = state.profile
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.account),
                        contentDescription = "Profile Image",
                        modifier = Modifier.size(150.dp)
                    )
                    Text(
                        text = "First ${profile.firstName} Last ${profile.lastName}",
                        style = TextStyle(fontSize = 25.sp)
                    )
                    Text(
                        text = profile.patronimic,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    Text(
                        text = "Email: ${profile.email}",
                        style = TextStyle(fontSize = 15.sp)
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            token.value = authPreferences.getAuthToken().toString()
                            authPreferences.saveAuthToken("")
                            grpcClient.logoutStudent(token.value) { success ->
                                if (success) {

                                } else {
                                    // Show error message
                                }
                            }
                        }
                    }) {
                        Text(text = "Logout")
                    }
                }
            }
            is ProfileState.Error -> {
                Text(text = "Error: ${state.message}")
                Button(onClick = { /* Retry loading */ }) {
                    Text(text = "Retry")
                }
            }
        }
    }
}

sealed class ProfileState {
    object Loading : ProfileState()
    data class Loaded(val profile: GetStudentProfileDataResponse) : ProfileState()
    data class Error(val message: String) : ProfileState()
}