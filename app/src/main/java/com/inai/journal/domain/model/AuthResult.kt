package com.inai.journal.domain.model

data class AuthResult(
    val passwordError: String? = null,
    val emailError : String? = null,
)
