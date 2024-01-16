package com.example.dto.userSessions

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

typealias UserSessionId = Int

@Serializable
data class UserSessionDTO(
    val id: Int,
    val userId: Int,
    val token: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
