package com.example.repositories.userSessions

import com.example.dto.userSessions.CreateUserSessionDTO
import com.example.dto.userSessions.UpdateUserSessionDTO
import com.example.dto.userSessions.UserSessionDTO
import com.example.dto.userSessions.UserSessionId

interface UserSessionsRepository {
    suspend fun allUserSessions(): List<UserSessionDTO>
    suspend fun getUserSessionById(userSessionId: UserSessionId): UserSessionDTO
    suspend fun createUserSession(userSession: CreateUserSessionDTO): UserSessionDTO
    suspend fun updateUserSession(userSessionId: UserSessionId, userSession: UpdateUserSessionDTO)
    suspend fun deleteUserSession(userSessionId: UserSessionId)

}