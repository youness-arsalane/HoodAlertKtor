package com.example.repositories.userSessions

import com.example.db.DatabaseFactory.dbQuery
import com.example.db.dao.UserSessionEntity
import com.example.db.dao.toUserSessionDTO
import com.example.dto.userSessions.CreateUserSessionDTO
import com.example.dto.userSessions.UpdateUserSessionDTO
import com.example.dto.userSessions.UserSessionDTO
import com.example.dto.userSessions.UserSessionId
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class UserSessionsRepositoryImpl : UserSessionsRepository {

    override suspend fun allUserSessions(): List<UserSessionDTO> = dbQuery {
        UserSessionEntity.all().map {
            it.toUserSessionDTO()
        }
    }

    override suspend fun getUserSessionById(userSessionId: UserSessionId): UserSessionDTO = dbQuery {
        UserSessionEntity.findById(userSessionId)?.toUserSessionDTO() ?: throw Exception("UserSession not found")
    }

    override suspend fun createUserSession(userSession: CreateUserSessionDTO): UserSessionDTO = dbQuery {
        UserSessionEntity.new {
            userId = userSession.userId
            token = userSession.token
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        }.toUserSessionDTO()
    }

    override suspend fun updateUserSession(userSessionId: UserSessionId, userSession: UpdateUserSessionDTO) = dbQuery {
        UserSessionEntity.findById(userSessionId)?.let {
            it.userId = userSession.userId
            it.token = userSession.token
            it.updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        } ?: throw Exception("UserSession not found")
    }

    override suspend fun deleteUserSession(userSessionId: UserSessionId) = dbQuery {
        UserSessionEntity.findById(userSessionId)?.delete() ?: throw Exception("UserSession not found")
    }
}

val userSessionsRepository: UserSessionsRepository = UserSessionsRepositoryImpl()
