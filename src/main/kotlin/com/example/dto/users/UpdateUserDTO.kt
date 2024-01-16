package com.example.dto.users

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserDTO(
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)