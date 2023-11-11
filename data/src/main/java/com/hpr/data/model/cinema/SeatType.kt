package com.hpr.data.model.cinema

import androidx.compose.ui.graphics.Color


enum class SeatType(
    val seatCombColor: Color,
    val seatColor: Color
) {
    Available(Color(0xFF4f4f4f), Color.White),
    Taken(Color(0xFF1d1d1d), Color(0xFF3e3e3e)),
    Selected(Color(0xfffa5422), Color(0xfffa5422))
}
