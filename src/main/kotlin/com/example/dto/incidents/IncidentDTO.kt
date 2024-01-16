package com.example.dto.incidents

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

typealias IncidentId = Int

@Serializable
data class IncidentDTO(
    val id: Int,
    val communityId: Int,
    val userId: Int,
    val title: String,
    val description: String,
    val street: String,
    val houseNumber: String,
    val zipcode: String,
    val city: String,
    val country: String,
    val latitude: Double?,
    val longitude: Double?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
