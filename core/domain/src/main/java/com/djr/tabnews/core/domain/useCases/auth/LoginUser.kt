package com.djr.tabnews.core.domain.useCases.auth

import com.djr.tabnews.core.data.repository.auth.AuthRepository
import com.djr.tabnews.core.domain.Resource
import com.djr.tabnews.core.models.auth.LoginModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUser @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        loginModel: LoginModel
    ): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        authRepository.login(loginModel)
            .catch { e -> emit(Resource.Error(e.message.toString())) }
            .collect {
                emit(Resource.Success(it))
            }
    }

}