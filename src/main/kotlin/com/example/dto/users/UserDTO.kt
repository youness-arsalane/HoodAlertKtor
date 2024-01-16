package com.example.dto.users

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

typealias UserId = Int

@Serializable
data class UserDTO(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
