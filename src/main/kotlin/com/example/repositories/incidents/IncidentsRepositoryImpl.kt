package com.example.repositories.incidents

import com.example.db.DatabaseFactory.dbQuery
import com.example.db.dao.IncidentEntity
import com.example.db.dao.toIncidentDTO
import com.example.dto.incidents.CreateIncidentDTO
import com.example.dto.incidents.UpdateIncidentDTO
import com.example.dto.incidents.IncidentDTO
import com.example.dto.incidents.IncidentId
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class IncidentsRepositoryImpl : IncidentsRepository {

    override suspend fun allIncidents(): List<IncidentDTO> = dbQuery {
        IncidentEntity.all().map {
            it.toIncidentDTO()
        }
    }

    override suspend fun getIncidentById(incidentId: IncidentId): IncidentDTO = dbQuery {
        IncidentEntity.findById(incidentId)?.toIncidentDTO() ?: throw Exception("Incident not found")
    }

    override suspend fun createIncident(incident: CreateIncidentDTO): IncidentDTO = dbQuery {
        IncidentEntity.new {
            communityId = incident.communityId
            userId = incident.userId
            title = incident.title
            description = incident.description
            street = incident.street
            houseNumber = incident.houseNumber
            zipcode = incident.zipcode
            city = incident.city
            country = incident.country
            latitude = incident.latitude
            longitude = incident.longitude
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        }.toIncidentDTO()
    }

    override suspend fun updateIncident(incidentId: IncidentId, incident: UpdateIncidentDTO) = dbQuery {
        IncidentEntity.findById(incidentId)?.let {
            it.communityId = incident.communityId
            it.userId = incident.userId
            it.title = incident.title
            it.description = incident.description
            it.street = incident.street
            it.houseNumber = incident.houseNumber
            it.zipcode = incident.zipcode
            it.city = incident.city
            it.country = incident.country
            it.latitude = incident.latitude
            it.longitude = incident.longitude
            it.updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        } ?: throw Exception("Incident not found")
    }

    override suspend fun deleteIncident(incidentId: IncidentId) = dbQuery {
        IncidentEntity.findById(incidentId)?.delete() ?: throw Exception("Incident not found")
    }
}

val incidentsRepository: IncidentsRepository = IncidentsRepositoryImpl()
