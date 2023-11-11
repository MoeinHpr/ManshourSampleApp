package com.hpr.data.dataSource.cinema

import com.hpr.data.api.AppNetworkResponse
import com.hpr.data.api.CinemaApi
import com.hpr.data.dataSource.base.BaseDataSourceNetwork
import com.hpr.data.model.cinema.BuyTicketRequest
import com.hpr.data.model.cinema.BuyTicketResponse
import javax.inject.Inject

class CinemaDataSourceNetwork @Inject constructor(
    private val cinemaApi: CinemaApi
) : BaseDataSourceNetwork() {

    suspend fun buyTicket(
        buyTicketRequest: BuyTicketRequest
    ): AppNetworkResponse<BuyTicketResponse> {
        return checkResult(cinemaApi.buyTicket(buyTicketRequest))
    }
}