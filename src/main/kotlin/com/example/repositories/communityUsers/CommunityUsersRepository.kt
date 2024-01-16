package com.example.repositories.communityUsers

import com.example.dto.communityUsers.CreateCommunityUserDTO
import com.example.dto.communityUsers.UpdateCommunityUserDTO
import com.example.dto.communityUsers.CommunityUserDTO
import com.example.dto.communityUsers.CommunityUserId

interface CommunityUsersRepository {
    suspend fun allCommunityUsers(): List<CommunityUserDTO>
    suspend fun getCommunityUserById(communityUserId: CommunityUserId): CommunityUserDTO
    suspend fun createCommunityUser(communityUser: CreateCommunityUserDTO): CommunityUserDTO
    suspend fun updateCommunityUser(communityUserId: CommunityUserId, communityUser: UpdateCommunityUserDTO)
    suspend fun deleteCommunityUser(communityUserId: CommunityUserId)

}