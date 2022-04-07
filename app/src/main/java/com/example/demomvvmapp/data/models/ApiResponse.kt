package com.example.demomvvmapp.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
/*
    Generic Response from an API
 */
data class SuccessResponse(
    @Json(name = "success")
    val success : Int?,
    @Json(name = "error")
    val error : String?
)