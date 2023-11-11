package com.hpr.cinema

import androidx.lifecycle.viewModelScope
import com.hpr.data.base.BaseViewModel
import com.hpr.data.model.cinema.BuyTicketRequest
import com.hpr.data.repository.cinema.CinemaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CinemaViewModel @Inject constructor(
    private val cinemaRepository: CinemaRepository
) : BaseViewModel() {

    val seatDataList = cinemaRepository.getSeatDataLocal()
    val dayDataList = cinemaRepository.getDayDataLocal()
    val hourDataList = cinemaRepository.getHourDataLocal()

    private val _ticketResult = MutableStateFlow("")
    val ticketResult : Flow<String>
        get() = _ticketResult

    fun buyTicket(buyTicketRequest: BuyTicketRequest) {
        _ticketResult.value = ""
        viewModelScope.launch {
            callEnqueue(
                request = {
                    cinemaRepository.getCarsNetwork(buyTicketRequest)
                },
                onSuccess = {
                    _ticketResult.emit(it.status)
                },
                onServerError = {
                    _ticketResult.value = it.message
                },
                onAppException = {
                    _ticketResult.value = it.message
                },
            )
        }
    }

}