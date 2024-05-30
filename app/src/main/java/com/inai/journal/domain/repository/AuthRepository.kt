package com.inai.journal.domain.repository

import auth.LoginStudentRequest
import auth.LoginStudentResponse
import auth.RegisterStudentRequest
import auth.RegisterStudentResponse

interface AuthRepository {

    suspend fun login(user: LoginStudentRequest):LoginStudentResponse

    suspend fun register(user:RegisterStudentRequest):RegisterStudentResponse
}