package com.example.dto.communityUsers

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

typealias CommunityUserId = Int

@Serializable
data class CommunityUserDTO(
    val id: Int,
    val communityId: Int,
    val userId: Int,
    val isAdmin: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
