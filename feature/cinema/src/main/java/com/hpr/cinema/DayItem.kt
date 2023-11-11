package com.hpr.cinema

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.hpr.data.model.cinema.WeekDay

@Composable
fun DayItem(modifier: Modifier, weekDay: WeekDay, selected: Boolean, onClick: () -> Unit) {
   /* var selectedDay by remember {
        mutableStateOf(WeekDay())
    }*/

    Column(
        modifier = modifier
            .padding(start = 4.dp, end = 4.dp)
            .wrapContentSize()
            .border(0.5.dp, Color(0xffe7e7e7), RoundedCornerShape(16.dp))
            .background(
                if (selected)
                    Color(0xff928b82)
                else Color.Transparent,
                RoundedCornerShape(16.dp)
            )
            .padding(end = 12.dp, start = 12.dp, top = 6.dp, bottom = 6.dp)
            .clickable {
                onClick.invoke()
            },
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = weekDay.dateDayNum.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = TextUnit(18f, TextUnitType.Sp),
            color = if (selected) Color.White else Color.Black
        )
        Text(
            text = weekDay.weekName,
            fontWeight = FontWeight.ExtraLight,
            fontSize = TextUnit(12f, TextUnitType.Sp),
            color = if (selected) Color.White else Color.Gray
        )
    }

}

@Preview
@Composable
fun Preview1() {
   // DayItem(modifier = Modifier, WeekDay(12, "Sun"))
}