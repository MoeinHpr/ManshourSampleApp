package com.hpr.data.repository.cinema

import com.hpr.data.api.AppNetworkResponse
import com.hpr.data.dataSource.cinema.CinemaDataSourceLocal
import com.hpr.data.dataSource.cinema.CinemaDataSourceNetwork
import com.hpr.data.model.cinema.BuyTicketRequest
import com.hpr.data.model.cinema.BuyTicketResponse
import com.hpr.data.model.cinema.CinemaSeatComb
import com.hpr.data.model.cinema.WeekDay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CinemaRepositoryImpl @Inject constructor(
    private val cinemaDataSourceNetwork: CinemaDataSourceNetwork,
    private val cinemaDataSourceLocal: CinemaDataSourceLocal
) : CinemaRepository {

    override suspend fun getCarsNetwork(buyTicketRequest: BuyTicketRequest): AppNetworkResponse<BuyTicketResponse> {
        return cinemaDataSourceNetwork.buyTicket(buyTicketRequest)
    }

    override fun getSeatDataLocal(): List<CinemaSeatComb> {
        return cinemaDataSourceLocal.generateSeatData()
    }

    override fun getDayDataLocal(): List<WeekDay> {
        return cinemaDataSourceLocal.generateWeekDayData()
    }

    override fun getHourDataLocal(): List<String> {
        return cinemaDataSourceLocal.generateHourData()
    }
}