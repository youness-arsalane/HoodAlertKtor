package com.example.routes.incidents

import com.example.dto.incidents.CreateIncidentDTO
import com.example.dto.incidents.UpdateIncidentDTO
import com.example.repositories.incidents.IncidentsRepositoryImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun Route.incidentRoutes() {
    val incidentsRepository = IncidentsRepositoryImpl()

    route("/incidents") {
        get {
            val incidents = incidentsRepository.allIncidents()
            call.respond(incidents)
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw Exception("Wrong format")
            val incident = incidentsRepository.getIncidentById(id)
            incident.let { call.respond(it) }
        }

        post {
            val createIncidentDTO = call.receive<CreateIncidentDTO>()
            val incident = incidentsRepository.createIncident(createIncidentDTO)
            call.respondText(Json.encodeToString(incident), status = HttpStatusCode.Created)
        }

        put("/{id}") {
            val updateIncidentDTO = call.receive<UpdateIncidentDTO>()
            val id = call.parameters["id"]?.toInt() ?: throw Exception("Wrong format")

            incidentsRepository.updateIncident(id, updateIncidentDTO)
            val updatedIncident = incidentsRepository.getIncidentById(id)
            call.respond(updatedIncident)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw Exception("Wrong format")
            call.respond(incidentsRepository.deleteIncident(id))
        }
    }
}
