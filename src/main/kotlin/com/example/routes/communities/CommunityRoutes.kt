package com.example.routes.communities

import com.example.dto.communities.CreateCommunityDTO
import com.example.dto.communities.UpdateCommunityDTO
import com.example.repositories.communities.CommunitiesRepositoryImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun Route.communityRoutes() {
    val communitiesRepository = CommunitiesRepositoryImpl()

    route("/communities") {
        get {
            val communities = communitiesRepository.allCommunities()
            call.respond(communities)
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw Exception("Wrong format")
            val community = communitiesRepository.getCommunityById(id)
            community.let { call.respond(it) }
        }

        post {
            val createCommunityDTO = call.receive<CreateCommunityDTO>()
            val community = communitiesRepository.createCommunity(createCommunityDTO)
            call.respondText(Json.encodeToString(community), status = HttpStatusCode.Created)
        }

        put("/{id}") {
            val updateCommunityDTO = call.receive<UpdateCommunityDTO>()
            val id = call.parameters["id"]?.toInt() ?: throw Exception("Wrong format")

            communitiesRepository.updateCommunity(id, updateCommunityDTO)
            val updatedCommunity = communitiesRepository.getCommunityById(id)
            call.respond(updatedCommunity)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw Exception("Wrong format")
            call.respond(communitiesRepository.deleteCommunity(id))
        }
    }
}
