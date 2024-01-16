package com.example.repositories.communityUsers

import com.example.db.DatabaseFactory.dbQuery
import com.example.db.dao.CommunityUserEntity
import com.example.db.dao.toCommunityUserDTO
import com.example.dto.communityUsers.CreateCommunityUserDTO
import com.example.dto.communityUsers.UpdateCommunityUserDTO
import com.example.dto.communityUsers.CommunityUserDTO
import com.example.dto.communityUsers.CommunityUserId

class CommunityUsersRepositoryImpl : CommunityUsersRepository {

    override suspend fun allCommunityUsers(): List<CommunityUserDTO> = dbQuery {
        CommunityUserEntity.all().map {
            it.toCommunityUserDTO()
        }
    }

    override suspend fun getCommunityUserById(communityUserId: CommunityUserId): CommunityUserDTO = dbQuery {
        CommunityUserEntity.findById(communityUserId)?.toCommunityUserDTO() ?: throw Exception("CommunityUser not found")
    }

    override suspend fun createCommunityUser(communityUser: CreateCommunityUserDTO): CommunityUserDTO = dbQuery {
        CommunityUserEntity.new {
            communityId = communityUser.communityId
            userId = communityUser.userId
            isAdmin = communityUser.isAdmin
            createdAt = communityUser.createdAt
            updatedAt = communityUser.updatedAt
        }.toCommunityUserDTO()
    }

    override suspend fun updateCommunityUser(communityUserId: CommunityUserId, communityUser: UpdateCommunityUserDTO) = dbQuery {
        CommunityUserEntity.findById(communityUserId)?.let {
            it.communityId = communityUser.communityId
            it.userId = communityUser.userId
            it.isAdmin = communityUser.isAdmin
            it.createdAt = communityUser.createdAt
            it.updatedAt = communityUser.updatedAt
        } ?: throw Exception("CommunityUser not found")
    }

    override suspend fun deleteCommunityUser(communityUserId: CommunityUserId) = dbQuery {
        CommunityUserEntity.findById(communityUserId)?.delete() ?: throw Exception("CommunityUser not found")
    }
}

val communityUsersRepository: CommunityUsersRepository = CommunityUsersRepositoryImpl()
