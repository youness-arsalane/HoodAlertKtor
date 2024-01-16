package com.example.db.dao

import com.example.db.tables.CommunitiesTable
import com.example.db.tables.UserSessionsTable
import com.example.dto.userSessions.UserSessionDTO
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserSessionEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserSessionEntity>(UserSessionsTable)

    var userId by UserSessionsTable.userId
    var token by UserSessionsTable.token
    var createdAt by CommunitiesTable.createdAt
    var updatedAt by CommunitiesTable.updatedAt
}

fun UserSessionEntity.toUserSessionDTO() = UserSessionDTO(
    this.id.value,
    this.userId,
    this.token,
    this.createdAt,
    this.updatedAt
)