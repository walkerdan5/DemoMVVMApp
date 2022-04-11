package com.example.demomvvmapp.data.api

import com.example.demomvvmapp.data.models.MoonPhaseResponse

interface ApiContract {

    suspend fun moonPhases(timestamp: Long): List<MoonPhaseResponse>
}