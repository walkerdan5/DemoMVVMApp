package com.example.demomvvmapp.data.api

import com.example.demomvvmapp.data.models.MoonPhaseResponse
import java.sql.Timestamp

class ProjectAPI(private val apiService: ApiService): ApiContract {

    override suspend fun moonPhases(headers: Map<String, String>, timestamp: Timestamp): MoonPhaseResponse {
        return apiService.getMoonPhases(headers, timestamp)
    }

}