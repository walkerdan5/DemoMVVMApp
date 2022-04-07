package com.example.demomvvmapp.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoonPhaseResponse(
    @Json(name = "Error") val error: Int,
    @Json(name = "ErrorMsg") val ErrorMsg: String,
    @Json(name = "TargetDate") val TargetDate: String,
    @Json(name = "Moon") val Moon: ArrayList<String> = arrayListOf(),
    @Json(name = "Index") val Index: Int,
    @Json(name = "Age") val Age: Double,
    @Json(name = "Phase") val Phase: String,
    @Json(name = "Distance") val Distance: Double,
    @Json(name = "Illumination") val Illumination: Double,
    @Json(name = "AngularDiameter") val AngularDiameter: Double,
    @Json(name = "DistanceToSun") val DistanceToSun: Double,
    @Json(name = "SunAngularDiameter") val SunAngularDiameter: Double?
)

