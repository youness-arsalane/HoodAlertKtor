package com.example.repositories.incidents

import com.example.dto.incidents.CreateIncidentDTO
import com.example.dto.incidents.UpdateIncidentDTO
import com.example.dto.incidents.IncidentDTO
import com.example.dto.incidents.IncidentId

interface IncidentsRepository {
    suspend fun allIncidents(): List<IncidentDTO>
    suspend fun getIncidentById(incidentId: IncidentId): IncidentDTO
    suspend fun createIncident(incident: CreateIncidentDTO): IncidentDTO
    suspend fun updateIncident(incidentId: IncidentId, incident: UpdateIncidentDTO)
    suspend fun deleteIncident(incidentId: IncidentId)

}