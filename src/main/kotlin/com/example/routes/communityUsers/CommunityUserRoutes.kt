package com.example.routes.communityUsers

import com.example.dto.communityUsers.CreateCommunityUserDTO
import com.example.dto.communityUsers.UpdateCommunityUserDTO
import com.example.repositories.communityUsers.CommunityUsersRepositoryImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun Route.communityUserRoutes() {
    val communityUsersRepository = CommunityUsersRepositoryImpl()

    route("/communityUsers") {
        get {
            val communityUsers = communityUsersRepository.allCommunityUsers()
            call.respond(communityUsers)
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw Exception("Wrong format")
            val communityUser = communityUsersRepository.getCommunityUserById(id)
            communityUser.let { call.respond(it) }
        }

        post {
            val createCommunityUserDTO = call.receive<CreateCommunityUserDTO>()
            val communityUser = communityUsersRepository.createCommunityUser(createCommunityUserDTO)
            call.respondText(Json.encodeToString(communityUser), status = HttpStatusCode.Created)
        }

        put("/{id}") {
            val updateCommunityUserDTO = call.receive<UpdateCommunityUserDTO>()
            val id = call.parameters["id"]?.toInt() ?: throw Exception("Wrong format")

            communityUsersRepository.updateCommunityUser(id, updateCommunityUserDTO)
            val updatedCommunityUser = communityUsersRepository.getCommunityUserById(id)
            call.respond(updatedCommunityUser)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw Exception("Wrong format")
            call.respond(communityUsersRepository.deleteCommunityUser(id))
        }
    }
}
