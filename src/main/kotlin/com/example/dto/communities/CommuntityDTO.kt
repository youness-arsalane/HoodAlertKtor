package com.example.dto.communities

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

typealias CommunityId = Int

@Serializable
data class CommunityDTO(
    val id: Int,
    val name: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
