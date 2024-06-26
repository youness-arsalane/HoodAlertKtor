package com.example.repositories.users

import com.example.db.DatabaseFactory.dbQuery
import com.example.db.dao.CommunityUserEntity
import com.example.db.dao.UserEntity
import com.example.db.dao.toCommunityUserDTO
import com.example.db.dao.toUserDTO
import com.example.db.tables.CommunityUsersTable
import com.example.db.tables.UsersTable
import com.example.dto.communityUsers.CommunityUserDTO
import com.example.dto.users.CreateUserDTO
import com.example.dto.users.UpdateUserDTO
import com.example.dto.users.UserDTO
import com.example.dto.users.UserId
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class UsersRepositoryImpl : UsersRepository {

    override suspend fun allUsers(): List<UserDTO> = dbQuery {
        UserEntity.all().map {
            it.toUserDTO()
        }
    }

    override suspend fun getUserById(userId: UserId): UserDTO = dbQuery {
        UserEntity.findById(userId)?.toUserDTO() ?: throw Exception("User not found")
    }

    override suspend fun getUserByEmail(email: String): UserDTO = dbQuery {
        UserEntity.find {
            UsersTable.email eq email
        }.firstOrNull()?.toUserDTO() ?: throw Exception("User not found")
    }

    override suspend fun createUser(user: CreateUserDTO): UserDTO = dbQuery {
        UserEntity.new {
            email = user.email
            firstName = user.firstName
            lastName = user.lastName
            password = user.password
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        }.toUserDTO()
    }

    override suspend fun updateUser(userId: UserId, user: UpdateUserDTO) = dbQuery {
        UserEntity.findById(userId)?.let {
            it.email = user.email
            it.firstName = user.firstName
            it.lastName = user.lastName
            it.password = user.password
            it.updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        } ?: throw Exception("User not found")
    }

    override suspend fun deleteUser(userId: UserId) = dbQuery {
        UserEntity.findById(userId)?.delete() ?: throw Exception("User not found")
    }
}

val usersRepository: UsersRepository = UsersRepositoryImpl()
