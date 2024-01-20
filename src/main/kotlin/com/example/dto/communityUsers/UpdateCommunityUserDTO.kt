package com.example.dto.communityUsers

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Serializable
data class UpdateCommunityUserDTO(
    val communityId: Int,
    val userId: Int,
    val isAdmin: Boolean
)