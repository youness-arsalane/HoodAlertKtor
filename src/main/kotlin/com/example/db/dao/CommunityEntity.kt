package com.example.db.dao

import com.example.db.tables.CommunitiesTable
import com.example.dto.communities.CommunityDTO
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CommunityEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CommunityEntity>(CommunitiesTable)

    var name by CommunitiesTable.name
    var createdAt by CommunitiesTable.createdAt
    var updatedAt by CommunitiesTable.updatedAt
}

fun CommunityEntity.toCommunityDTO() = CommunityDTO(
    this.id.value,
    this.name,
    this.createdAt,
    this.updatedAt
)