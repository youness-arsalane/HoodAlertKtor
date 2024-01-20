package com.example.dto.communities

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Serializable
data class UpdateCommunityDTO(
    val name: String
)