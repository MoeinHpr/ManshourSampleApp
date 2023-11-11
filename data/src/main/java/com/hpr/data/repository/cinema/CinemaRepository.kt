package com.hpr.data.repository.cinema

import com.hpr.data.api.AppNetworkResponse
import com.hpr.data.model.cinema.BuyTicketRequest
import com.hpr.data.model.cinema.BuyTicketResponse
import com.hpr.data.model.cinema.CinemaSeatComb
import com.hpr.data.model.cinema.WeekDay

interface CinemaRepository {

    suspend fun getCarsNetwork(buyTicketRequest: BuyTicketRequest): AppNetworkResponse<BuyTicketResponse>

    fun getSeatDataLocal() : List<CinemaSeatComb>
    fun getDayDataLocal() : List<WeekDay>
    fun getHourDataLocal() : List<String>
}