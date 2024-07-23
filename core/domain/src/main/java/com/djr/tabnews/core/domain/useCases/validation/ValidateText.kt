package com.djr.tabnews.core.domain.useCases.validation

import com.djr.tabnews.core.domain.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ValidateText @Inject constructor() {
    operator fun invoke(
        text: String
    ): Flow<Resource<Unit>> = flow {
        when {
            text.isBlank() -> emit(Resource.Error("O campo não pode estar em branco"))
            else -> emit(Resource.Success(Unit))
        }
    }
}