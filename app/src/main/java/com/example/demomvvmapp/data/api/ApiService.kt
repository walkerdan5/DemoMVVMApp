package com.example.demomvvmapp.data.api

import com.example.demomvvmapp.data.models.MoonPhaseResponse
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query
import java.sql.Timestamp

interface ApiService {

    @GET(ProjectEndpoints.MOON_PHASES)
    suspend fun getMoonPhases(@HeaderMap headers: Map<String, String>, @Query("d") timestamp: Timestamp): MoonPhaseResponse
}