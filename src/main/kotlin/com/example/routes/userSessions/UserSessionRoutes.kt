package com.example.routes.userSessions

import com.example.dto.userSessions.CreateUserSessionDTO
import com.example.dto.userSessions.UpdateUserSessionDTO
import com.example.repositories.userSessions.UserSessionsRepositoryImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun Route.userSessionRoutes() {
    val userSessionsRepository = UserSessionsRepositoryImpl()

    route("/userSessions") {
        get {
            val userSessions = userSessionsRepository.allUserSessions()
            call.respond(userSessions)
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw Exception("Wrong format")
            val userSession = userSessionsRepository.getUserSessionById(id)
            userSession.let { call.respond(it) }
        }

        post {
            val createUserSessionDTO = call.receive<CreateUserSessionDTO>()
            val userSession = userSessionsRepository.createUserSession(createUserSessionDTO)
            call.respondText(Json.encodeToString(userSession), status = HttpStatusCode.Created)
        }

        put("/{id}") {
            val updateUserSessionDTO = call.receive<UpdateUserSessionDTO>()
            val id = call.parameters["id"]?.toInt() ?: throw Exception("Wrong format")

            userSessionsRepository.updateUserSession(id, updateUserSessionDTO)
            val updatedUserSession = userSessionsRepository.getUserSessionById(id)
            call.respond(updatedUserSession)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw Exception("Wrong format")
            call.respond(userSessionsRepository.deleteUserSession(id))
        }
    }
}
