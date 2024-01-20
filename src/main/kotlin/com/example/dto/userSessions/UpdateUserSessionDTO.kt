package com.example.dto.userSessions

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Serializable
data class UpdateUserSessionDTO(
    val userId: Int,
    val token: String
)