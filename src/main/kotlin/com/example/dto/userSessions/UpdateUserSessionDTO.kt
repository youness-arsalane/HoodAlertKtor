package com.example.dto.userSessions

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserSessionDTO(
    val userId: Int,
    val token: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)