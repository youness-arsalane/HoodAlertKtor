package com.example.db.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object UsersTable : IntIdTable() {
    val email = varchar("email", 255).default("")
    val firstName = varchar("first_name", 255).default("")
    val lastName = varchar("last_name", 255).default("")
    val password = varchar("password", 255).default("")
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
}