package com.djr.tabnews.features.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djr.tabnews.core.domain.Resource
import com.djr.tabnews.core.domain.useCases.auth.LoginUser
import com.djr.tabnews.core.domain.useCases.validation.ValidateEmail
import com.djr.tabnews.core.domain.useCases.validation.ValidateText
import com.djr.tabnews.core.models.auth.LoginModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUser: LoginUser,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidateText
) : ViewModel() {

    private val _loginUiState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val loginUiState: StateFlow<LoginState> = _loginUiState

    private fun handleForm(
        email: String,
        password: String,
        onLoginSuccess: (String) -> Unit
    ) {
        viewModelScope.launch {
            handleEmail(email)
            handlePassword(password)
            if (loginUiState.value.isEmailValid && loginUiState.value.isPasswordValid) {
                handleLogin(email, password, onLoginSuccess)
            }
        }
    }

    private suspend fun handleEmail(email: String) {
        validateEmail.invoke(email).collect {
            when (it) {
                is Resource.Success -> {
                    _loginUiState.update { state -> state.copy(isEmailValid = true) }
                }
                is Resource.Error -> {
                    _loginUiState.update { state -> state.copy(isEmailValid = false) }
                }
                else -> Unit
            }
        }
    }

    private suspend fun handlePassword(password: String) {
        validatePassword.invoke(password).collect {
            when (it) {
                is Resource.Success -> {
                    _loginUiState.update { state -> state.copy(isPasswordValid = true) }
                }
                is Resource.Error -> {
                    println(it.message)
                    _loginUiState.update { state -> state.copy(isPasswordValid = false) }
                }
                else -> Unit
            }
        }
    }

    fun sendEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.ChangeEmail -> {
                _loginUiState.update { it.copy(email = event.email.trim(), isEmailValid = true) }
            }
            is LoginUiEvent.ChangePassword -> {
                _loginUiState.update { it.copy(password = event.password.trim()) }
            }
            is LoginUiEvent.ValidateForm -> {
                handleForm(event.email, event.password, event.onLoginSuccess)
            }
        }
    }

    private fun handleLogin(
        email: String,
        password: String,
        onLoginSuccess: (String) -> Unit
    ) {
        viewModelScope.launch {
            loginUser.invoke(LoginModel(email, password)).collect { res ->
                when (res) {
                    is Resource.Success -> {
                        res.data?.let { data ->
                            onLoginSuccess.invoke(data)
                            _loginUiState.update {
                                it.copy(
                                    loginError = "", loading = false,
                                )
                            }
                        }
                    }
                    is Resource.Loading -> {
                        _loginUiState.update { it.copy(loginError = "", loading = true) }
                    }
                    is Resource.Error -> {
                        _loginUiState.update {
                            it.copy(
                                loginError = "Unable to login!",
                                loading = false
                            )
                        }
                    }
                }
            }
        }
    }

}