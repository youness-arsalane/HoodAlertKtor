package com.example.db.dao

import com.example.db.tables.CommunitiesTable
import com.example.db.tables.IncidentsTable
import com.example.dto.incidents.IncidentDTO
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class IncidentEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<IncidentEntity>(IncidentsTable)

    var communityId by IncidentsTable.communityId
    var userId by IncidentsTable.userId
    var title by IncidentsTable.title
    var description by IncidentsTable.description
    var street by IncidentsTable.street
    var houseNumber by IncidentsTable.houseNumber
    var zipcode by IncidentsTable.zipcode
    var city by IncidentsTable.city
    var country by IncidentsTable.country
    var latitude by IncidentsTable.latitude
    var longitude by IncidentsTable.longitude
    var createdAt by CommunitiesTable.createdAt
    var updatedAt by CommunitiesTable.updatedAt
}

fun IncidentEntity.toIncidentDTO() = IncidentDTO(
    this.id.value,
    this.communityId,
    this.userId,
    this.title,
    this.description,
    this.street,
    this.houseNumber,
    this.zipcode,
    this.city,
    this.country,
    this.latitude,
    this.longitude,
    this.createdAt,
    this.updatedAt
)