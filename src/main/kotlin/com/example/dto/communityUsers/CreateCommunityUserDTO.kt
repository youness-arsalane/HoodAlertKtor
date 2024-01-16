package com.example.dto.communityUsers

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class CreateCommunityUserDTO(
    val communityId: Int,
    val userId: Int,
    val isAdmin: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
