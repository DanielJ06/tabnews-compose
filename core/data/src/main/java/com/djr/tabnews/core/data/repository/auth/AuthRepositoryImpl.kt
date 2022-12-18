package com.djr.tabnews.core.data.repository.auth

import com.djr.tabnews.core.models.auth.LoginModel
import com.djr.tabnews.core.network.dataSource.auth.AuthNetworkDts
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authNetworkDts: AuthNetworkDts
) : AuthRepository {

    override suspend fun login(loginModel: LoginModel): Flow<String> {
        return authNetworkDts.login(loginModel)
    }

}