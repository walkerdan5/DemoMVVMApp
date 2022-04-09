package com.example.demomvvmapp.data.api

import com.example.demomvvmapp.data.models.MoonPhaseResponse

class ProjectAPI(private val apiService: ApiService): ApiContract {

    override suspend fun moonPhases(timestamp: Long): List<MoonPhaseResponse> {
        return apiService.getMoonPhases(timestamp)
    }

}