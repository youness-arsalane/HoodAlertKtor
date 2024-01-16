package com.example.db.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object IncidentsTable : IntIdTable() {
    val communityId = integer("community_id")
    val userId = integer("user_id")
    val title = varchar("title", 255).default("")
    val description = varchar("description", 255).default("")
    val street = varchar("street", 255).default("")
    val houseNumber = varchar("house_number", 255).default("")
    val zipcode = varchar("zipcode", 255).default("")
    val city = varchar("city", 255).default("")
    val country = varchar("country", 255).default("")
    val latitude = double("latitude").nullable().default(null)
    val longitude = double("longitude").nullable().default(null)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
}