package com.djr.tabnews.core.domain.useCases.validation

import com.djr.tabnews.core.commons.extensions.isNotEmail
import com.djr.tabnews.core.domain.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ValidateEmail @Inject constructor() {
    operator fun invoke(
        email: String
    ): Flow<Resource<Unit>> = flow {
        when {
            email.isBlank() -> emit(Resource.Error("O campo não pode estar em branco"))
            email.isNotEmail() -> emit(Resource.Error("Email inválido"))
            else -> emit(Resource.Success(Unit))
        }
    }
}