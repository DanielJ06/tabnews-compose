package com.djr.tabnews.core.network.dataSource.auth

import com.djr.tabnews.core.models.auth.LoginModel
import com.djr.tabnews.core.network.TabNewsService
import com.djr.tabnews.core.network.dtos.toRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthNetworkDtsImpl @Inject constructor(
    private val tabNewsService: TabNewsService
) : AuthNetworkDts {

    override suspend fun login(loginModel: LoginModel): Flow<String> = flow {
        emit(tabNewsService.signIn(loginModel.toRequest()).token)
    }

}