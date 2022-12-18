package com.djr.tabnews.core.data.repository.auth

import com.djr.tabnews.core.models.auth.LoginModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(loginModel: LoginModel): Flow<String>
}