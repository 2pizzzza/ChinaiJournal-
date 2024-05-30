package com.inai.journal.platform

import android.util.Log
import auth.AuthGrpc
import auth.GetStudentProfileDataResponse
import auth.LoginStudentRequest
import auth.LogoutStudentRequest
import com.google.protobuf.Empty
import com.inai.journal.data.local.AuthPreferences
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.stub.MetadataUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GrpcClient(private val authPreferences: AuthPreferences) {
    private val channel: ManagedChannel by lazy {
        ManagedChannelBuilder.forAddress("192.168.169.234", 9999)
            .usePlaintext()
            .build()
    }

    init {
        Runtime.getRuntime().addShutdownHook(Thread {
            channel.shutdown()
        })
    }

    fun getStudentProfileData(onResult: (GetStudentProfileDataResponse?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val token = authPreferences.getAuthToken()
                if (token != null) {
                    val metadata = io.grpc.Metadata().apply {
                        put(io.grpc.Metadata.Key.of("Authorization", io.grpc.Metadata.ASCII_STRING_MARSHALLER), "Bearer $token")
                    }
                    val response = AuthGrpc.newBlockingStub(channel).withInterceptors(MetadataUtils.newAttachHeadersInterceptor(metadata)).getStudentProfileData(Empty.getDefaultInstance())
                    withContext(Dispatchers.Main) {
                        onResult(response)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onResult(null)
                    }
                }
            } catch (e: Exception) {
                Log.e("GrpcClient", "Failed to get profile data: ${e.message}")
                withContext(Dispatchers.Main) {
                    onResult(null)
                }
            }
        }
    }

    fun loginStudent(username: String, password: String, onResult: (String?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Example request, replace with your actual gRPC request
                val request = LoginStudentRequest.newBuilder()
                    .setStudentLogin(username)
                    .setPassword(password)
                    .build()
                val response = AuthGrpc.newBlockingStub(channel).loginStudent(request)
                authPreferences.saveAuthToken(response.accessToken)
                withContext(Dispatchers.Main) {
                    // Update UI or call any method that should be executed on the main thread
                }
                onResult(response.accessToken)
            } catch (e: Exception) {
                Log.e("GrpcClient", "Login failed: ${e.message}")
                withContext(Dispatchers.Main) {
                    onResult(null)
                }
            }
        }

    }

    fun logoutStudent(token: String, onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val request = LogoutStudentRequest.newBuilder()
                    .setToken(token)
                    .build()
                val response = AuthGrpc.newBlockingStub(channel).logoutStudent(request)
                withContext(Dispatchers.Main) {
                    onResult(response.success)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(false)
                }
            }
        }
    }
}





