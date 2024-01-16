package com.example.repositories.communities

import com.example.db.DatabaseFactory.dbQuery
import com.example.db.dao.CommunityEntity
import com.example.db.dao.toCommunityDTO
import com.example.dto.communities.CreateCommunityDTO
import com.example.dto.communities.UpdateCommunityDTO
import com.example.dto.communities.CommunityDTO
import com.example.dto.communities.CommunityId

class CommunitiesRepositoryImpl : CommunitiesRepository {

    override suspend fun allCommunities(): List<CommunityDTO> = dbQuery {
        CommunityEntity.all().map {
            it.toCommunityDTO()
        }
    }

    override suspend fun getCommunityById(communityId: CommunityId): CommunityDTO = dbQuery {
        CommunityEntity.findById(communityId)?.toCommunityDTO() ?: throw Exception("Community not found")
    }

    override suspend fun createCommunity(community: CreateCommunityDTO): CommunityDTO = dbQuery {
        CommunityEntity.new {
            name = community.name
            createdAt = community.createdAt
            updatedAt = community.updatedAt
        }.toCommunityDTO()
    }

    override suspend fun updateCommunity(communityId: CommunityId, community: UpdateCommunityDTO) = dbQuery {
        CommunityEntity.findById(communityId)?.let {
            it.name = community.name
            it.createdAt = community.createdAt
            it.updatedAt = community.updatedAt
        } ?: throw Exception("Community not found")
    }

    override suspend fun deleteCommunity(communityId: CommunityId) = dbQuery {
        CommunityEntity.findById(communityId)?.delete() ?: throw Exception("Community not found")
    }
}

val communitiesRepository: CommunitiesRepository = CommunitiesRepositoryImpl()
