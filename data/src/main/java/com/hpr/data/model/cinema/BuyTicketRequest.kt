package com.hpr.data.model.cinema

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BuyTicketRequest(
    @field:Json(name = "seat_Id") val seatId : String = "",
    @field:Json(name = "hour") val hour : String = "",
    @field:Json(name = "day") val day : String = "",
)
