package com.example.repositories.users

import com.example.dto.users.CreateUserDTO
import com.example.dto.users.UpdateUserDTO
import com.example.dto.users.UserDTO
import com.example.dto.users.UserId

interface UsersRepository {
    suspend fun allUsers(): List<UserDTO>
    suspend fun getUserById(userId: UserId): UserDTO
    suspend fun createUser(user: CreateUserDTO): UserDTO
    suspend fun updateUser(userId: UserId, user: UpdateUserDTO)
    suspend fun deleteUser(userId: UserId)

}