package com.example.repositories.communities

import com.example.dto.communities.CreateCommunityDTO
import com.example.dto.communities.UpdateCommunityDTO
import com.example.dto.communities.CommunityDTO
import com.example.dto.communities.CommunityId

interface CommunitiesRepository {
    suspend fun allCommunities(): List<CommunityDTO>
    suspend fun getCommunityById(communityId: CommunityId): CommunityDTO
    suspend fun createCommunity(community: CreateCommunityDTO): CommunityDTO
    suspend fun updateCommunity(communityId: CommunityId, community: UpdateCommunityDTO)
    suspend fun deleteCommunity(communityId: CommunityId)

}