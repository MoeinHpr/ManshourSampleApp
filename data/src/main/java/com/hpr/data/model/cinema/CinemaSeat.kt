package com.hpr.data.model.cinema

data class CinemaSeatComb(
    val leftSeat: CinemaSeat,
    val rightSeat: CinemaSeat,
    val degree: Float
)

data class CinemaSeat(
    val seatId: Int = 0,
    var seatType: SeatType = SeatType.Selected,
    val direction : DirectionType = DirectionType.Left,
    val price : Long = 50,
)


