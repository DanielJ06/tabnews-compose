package com.djr.tabnews.features.auth.login

data class LoginState(
    val email: String = "daniel.j.rodrigues06@gmail.com",
    val password: String = "Dan0606#",
    val loading: Boolean = false,
    val loginError: String = "",
    val isEmailValid: Boolean = true,
    val isPasswordValid: Boolean = true,
)