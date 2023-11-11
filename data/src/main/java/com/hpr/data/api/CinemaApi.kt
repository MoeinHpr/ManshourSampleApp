package com.hpr.data.api

import com.hpr.data.model.cinema.BuyTicketRequest
import com.hpr.data.model.cinema.BuyTicketResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CinemaApi {

    @POST("d80d097b-9d1f-4d30-8041-fa98e8eeae70")
    suspend fun buyTicket(
        @Body buyTicketRequest: BuyTicketRequest,
    ) : Response<BuyTicketResponse>

}