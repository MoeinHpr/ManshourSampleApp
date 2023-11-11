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
fun HourItem(modifier: Modifier, hour: String, selected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = modifier
            .padding(start = 2.dp, end = 2.dp)
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
            }
        ,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = hour,
            fontWeight = FontWeight.Normal,
            fontSize = TextUnit(14f, TextUnitType.Sp),
            color = if (selected) Color.White else Color.Black
        )
    }
}

@Preview
@Composable
fun Preview2() {
    //HourItem(modifier = Modifier, "10:30")
}