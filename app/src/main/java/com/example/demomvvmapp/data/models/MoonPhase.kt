package com.example.demomvvmapp.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoonPhase (

    @Json(name = "name")
    val name : String,

    @Json(name = "number")
    val number: String,

    @Json(name = "contact_id")
    val contactId: String? = null,

    var isSelected: Boolean = false
)