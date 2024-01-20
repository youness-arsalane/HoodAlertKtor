package com.example.repositories.communityUsers

import com.example.db.DatabaseFactory.dbQuery
import com.example.db.dao.CommunityUserEntity
import com.example.db.dao.toCommunityUserDTO
import com.example.db.tables.CommunityUsersTable
import com.example.dto.communityUsers.CreateCommunityUserDTO
import com.example.dto.communityUsers.UpdateCommunityUserDTO
import com.example.dto.communityUsers.CommunityUserDTO
import com.example.dto.communityUsers.CommunityUserId
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.sql.select

class CommunityUsersRepositoryImpl : CommunityUsersRepository {

    override suspend fun allCommunityUsers(): List<CommunityUserDTO> = dbQuery {
        CommunityUserEntity.all().map {
            it.toCommunityUserDTO()
        }
    }

    override suspend fun getCommunityUserById(communityUserId: CommunityUserId): CommunityUserDTO = dbQuery {
        CommunityUserEntity.findById(communityUserId)?.toCommunityUserDTO() ?: throw Exception("CommunityUser not found")
    }

    override suspend fun getCommunityUserByCommunityIdAndUserId(communityId: Int, userId: Int): CommunityUserDTO = dbQuery {
        CommunityUserEntity.find {
            CommunityUsersTable.communityId eq communityId
            CommunityUsersTable.userId eq userId
        }.firstOrNull()?.toCommunityUserDTO() ?: throw Exception("CommunityUser not found")
    }

    override suspend fun createCommunityUser(communityUser: CreateCommunityUserDTO): CommunityUserDTO = dbQuery {
        CommunityUserEntity.new {
            communityId = communityUser.communityId
            userId = communityUser.userId
            isAdmin = communityUser.isAdmin
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        }.toCommunityUserDTO()
    }

    override suspend fun updateCommunityUser(communityUserId: CommunityUserId, communityUser: UpdateCommunityUserDTO) = dbQuery {
        CommunityUserEntity.findById(communityUserId)?.let {
            it.communityId = communityUser.communityId
            it.userId = communityUser.userId
            it.isAdmin = communityUser.isAdmin
            it.updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        } ?: throw Exception("CommunityUser not found")
    }

    override suspend fun deleteCommunityUser(communityUserId: CommunityUserId) = dbQuery {
        CommunityUserEntity.findById(communityUserId)?.delete() ?: throw Exception("CommunityUser not found")
    }
}

val communityUsersRepository: CommunityUsersRepository = CommunityUsersRepositoryImpl()
