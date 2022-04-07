package com.example.demomvvmapp.data.api

import com.example.demomvvmapp.data.models.MoonPhase
import com.example.demomvvmapp.data.models.MoonPhaseResponse
import java.sql.Timestamp

interface ApiContract {

    suspend fun moonPhases(headers: Map<String, String>, timestamp: Timestamp): MoonPhaseResponse
}