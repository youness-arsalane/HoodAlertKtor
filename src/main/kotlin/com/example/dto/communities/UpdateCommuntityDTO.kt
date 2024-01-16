package com.example.dto.communities

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class UpdateCommunityDTO(
    val name: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)