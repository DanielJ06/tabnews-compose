package com.djr.tabnews.core.network.dataSource.auth

import com.djr.tabnews.core.models.auth.LoginModel
import kotlinx.coroutines.flow.Flow

interface AuthNetworkDts {
    suspend fun login(loginModel: LoginModel): Flow<String>
}