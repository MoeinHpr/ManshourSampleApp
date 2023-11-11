package com.hpr.data.model.cinema

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BuyTicketResponse(
    @Json(name = "status") val status: String = "",
)
