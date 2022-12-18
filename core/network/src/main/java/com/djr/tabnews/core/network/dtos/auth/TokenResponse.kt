package com.djr.tabnews.core.network.dtos.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    @SerialName("id") val id: String,
    @SerialName("token") val token: String,
    @SerialName("expires_at") val expires_at: String,
)
