package com.djr.tabnews.features.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.djr.tabnews.core.uikit.R
import com.djr.tabnews.core.uikit.components.button.FilledButton
import com.djr.tabnews.core.uikit.components.textField.BaseInput
import com.djr.tabnews.core.uikit.theme.TabNewsTheme
import com.djr.tabnews.core.uikit.theme.extensions.toColor
import com.djr.tabnews.core.uikit.theme.extensions.xxxsCorners

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToHome: () -> Unit
) {
    val state by viewModel.loginUiState.collectAsStateWithLifecycle()

    LoginScreen(
        state = state,
        onEvent = { e -> viewModel.sendEvent(e) },
        navigateToHome
    )
}

@Composable
fun LoginScreen(
    state: LoginState,
    onEvent: (LoginUiEvent) -> Unit,
    navigateToHome: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(TabNewsTheme.colors.primaryBg)
            .padding(horizontal = TabNewsTheme.spacing.Xs)
            .statusBarsPadding()
            .imePadding()
    ) {
        item {
            Spacer(modifier = Modifier.height(TabNewsTheme.spacing.Md))
            Box(
                modifier = Modifier
                    .xxxsCorners()
                    .background(TabNewsTheme.colors.textNeutralLight)
                    .padding(
                        horizontal = TabNewsTheme.spacing.Nano,
                        vertical = TabNewsTheme.spacing.Nano
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dark_logo),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            }
            Spacer(modifier = Modifier.height(TabNewsTheme.spacing.Xs))
            Text(
                text = "TabNews",
                style = TabNewsTheme.typography.HeadingH4,
                color = TabNewsTheme.colors.accentPrimary
            )
            Text(
                text = "Construindo um pedaço de internet mais massa!",
                style = TabNewsTheme.typography.HeadingH2,
                color = TabNewsTheme.colors.textNeutralLight
            )
            Spacer(modifier = Modifier.height(TabNewsTheme.spacing.Xs))
            BaseInput(
                value = state.email,
                onValueChange = { t -> onEvent.invoke(LoginUiEvent.ChangeEmail(t)) },
                placeholder = "Email",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                error = state.isEmailValid.not()
            )
            Spacer(modifier = Modifier.height(TabNewsTheme.spacing.Xxxs))
            BaseInput(
                value = state.password,
                onValueChange = { t -> onEvent.invoke(LoginUiEvent.ChangePassword(t)) },
                placeholder = "Senha",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(TabNewsTheme.spacing.Xxs))
            FilledButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    focusManager.clearFocus(true)
                    onEvent.invoke(
                        LoginUiEvent.ValidateForm(
                            state.email,
                            state.password
                        ) { navigateToHome.invoke() }
                    )
                },
                text = "Entrar",
                isLoading = state.loading,
                isEnable = state.email.isNotBlank() && state.password.isNotBlank()
            )
            Spacer(modifier = Modifier.height(TabNewsTheme.spacing.Xs))
            Text(
                color = TabNewsTheme.colors.textNeutralLight,
                style = TabNewsTheme.typography.TextNormal,
                text = "Ainda não tem uma conta? Criar".toColor(segment = "Criar"),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            if (state.loginError.isNotBlank()) {
                Text(
                    color = TabNewsTheme.colors.textNeutralLight,
                    style = TabNewsTheme.typography.TextNormal,
                    text = state.loginError,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(TabNewsTheme.spacing.Xs))
        }
    }
}