package com.djr.tabnews.features.auth.login

sealed class LoginUiEvent {
    data class ChangeEmail(val email: String) : LoginUiEvent()
    data class ChangePassword(val password: String) : LoginUiEvent()
    data class ValidateForm(
        val email: String,
        val password: String,
        val onLoginSuccess: (String) -> Unit
    ) : LoginUiEvent()
}
