package com.example.dto.communityUsers

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable

typealias CommunityUserId = Int

@Serializable
data class CommunityUserDTO(
    val id: Int,
    val communityId: Int,
    val userId: Int,
    val isAdmin: Boolean,
    val createdAt: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
    val updatedAt: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
)
