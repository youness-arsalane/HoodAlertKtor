package com.example.db.dao

import com.example.db.tables.CommunitiesTable
import com.example.db.tables.CommunityUsersTable
import com.example.dto.communityUsers.CommunityUserDTO
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CommunityUserEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CommunityUserEntity>(CommunityUsersTable)

    var communityId by CommunityUsersTable.communityId
    var userId by CommunityUsersTable.userId
    var isAdmin by CommunityUsersTable.isAdmin
    var createdAt by CommunitiesTable.createdAt
    var updatedAt by CommunitiesTable.updatedAt
}

fun CommunityUserEntity.toCommunityUserDTO() = CommunityUserDTO(
    this.id.value,
    this.communityId,
    this.userId,
    this.isAdmin,
    this.createdAt,
    this.updatedAt
)