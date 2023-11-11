package com.hpr.data.dataSource.cinema

import com.hpr.data.model.cinema.DirectionType
import com.hpr.data.model.cinema.CinemaSeatComb
import com.hpr.data.model.cinema.CinemaSeat
import com.hpr.data.model.cinema.SeatType
import com.hpr.data.model.cinema.WeekDay
import javax.inject.Inject

class CinemaDataSourceLocal @Inject constructor() {

    fun generateSeatData(): List<CinemaSeatComb> {
        val mockList = mutableListOf<CinemaSeatComb>()
        for (i in 0..11) {
            mockList.add(
                CinemaSeatComb(
                    if (i.mod(4) == 2)
                        CinemaSeat(i, SeatType.Taken, DirectionType.Left)
                    else
                        CinemaSeat(i, SeatType.Available, DirectionType.Left),
                    if (i.mod(5) == 3)
                        CinemaSeat(i + 1, SeatType.Taken, DirectionType.Right)
                    else CinemaSeat(i + 1, SeatType.Available, DirectionType.Right),
                    if (i.mod(3) == 0) 15f
                    else if (i.mod(3) == 1) 0f
                    else 345f
                )
            )
        }
        return mockList
    }

    fun generateWeekDayData(): List<WeekDay> {
        val days = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
        val weekDayList = mutableListOf<WeekDay>()
        for (i in 14..30) {
            weekDayList.add(
                WeekDay(i, days[i.mod(7)])
            )
        }
        return weekDayList
    }

    fun generateHourData(): List<String> =
        listOf("10:00", "12:30", "13:30", "18:00", "19:30", "20:00", "21:00")


}