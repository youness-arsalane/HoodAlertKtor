package com.example.plugins

import com.example.routes.incidents.incidentRoutes
import com.example.routes.communities.communityRoutes
import com.example.routes.communityUsers.communityUserRoutes
import com.example.routes.userSessions.userSessionRoutes
import com.example.routes.users.userRoutes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("{\"success\":\"true\"}", ContentType.Application.Json)
        }

        communityRoutes()
        communityUserRoutes()
        incidentRoutes()
        userRoutes()
        userSessionRoutes()
    }
}
