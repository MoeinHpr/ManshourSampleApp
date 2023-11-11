package com.hpr.cinema

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.hpr.data.R
import com.hpr.data.model.cinema.CinemaSeatComb
import com.hpr.data.model.cinema.CinemaSeat
import com.hpr.data.model.cinema.SeatType


@Composable
fun SeatItem(
    seatCombData: CinemaSeatComb,
    onClick: (CinemaSeat) -> Unit
) {
    val context = LocalContext.current
    val paddingTop = if (seatCombData.degree == 0f) 24.dp else 8.dp
    var seatData: CinemaSeatComb by remember {
        mutableStateOf(seatCombData)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 8.dp, start = 8.dp, top = paddingTop)
            .rotate(seatData.degree),
        contentAlignment = Alignment.Center
    ) {

        //SeatComb
        Icon(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.5f)
                /*.background(
                    Brush.horizontalGradient(
                        listOf(
                            seatData.leftSeat.seatType.seatCombColor,
                            seatData.rightSeat.seatType.seatCombColor,
                        )
                    )
                )*/,
            painter = painterResource(id = R.drawable.ic_seat_combination),
            tint = seatData.leftSeat.seatType.seatCombColor, //TODO fix this!
            contentDescription = null
        )
        Row(
            modifier = Modifier
                .padding(end = 10.dp, start = 10.dp)
                .matchParentSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            //Left Seat
            IconButton(
                modifier = Modifier
                    .weight(1f),
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = seatData.leftSeat.seatType.seatColor
                ),
                onClick = {
                    seatData = seatData.copy(
                        leftSeat = seatData.leftSeat.copy(
                            seatType = selectSeatType(seatData.leftSeat.seatType)
                        )
                    )
                    onClick.invoke(seatData.leftSeat)
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cinema_seat),
                    contentDescription = null
                )
            }

            //Right Seat
            IconButton(
                modifier = Modifier
                    .weight(1f),
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = seatData.rightSeat.seatType.seatColor
                ),
                onClick = {
                    seatData = seatData.copy(
                        rightSeat = seatData.rightSeat.copy(
                            seatType = selectSeatType(seatData.rightSeat.seatType)
                        )
                    )
                    onClick.invoke(seatData.rightSeat)
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cinema_seat),
                    contentDescription = null
                )
            }


        }
    }

}

fun selectSeatType(seatType: SeatType): SeatType {
    return when (seatType) {
        SeatType.Available -> SeatType.Selected
        SeatType.Selected -> SeatType.Available
        else -> SeatType.Taken
    }

}

private fun getBitmapFromImage(context: Context, drawable: Int): Bitmap {

    // on below line we are getting drawable
    val db = ContextCompat.getDrawable(context, drawable)

    // in below line we are creating our bitmap and initializing it.
    val bit = Bitmap.createBitmap(
        db!!.intrinsicWidth, db.intrinsicHeight, Bitmap.Config.ARGB_8888
    )

    // on below line we are
    // creating a variable for canvas.
    val canvas = Canvas(bit)

    // on below line we are setting bounds for our bitmap.
    db.setBounds(0, 0, canvas.width, canvas.height)

    // on below line we are simply
    // calling draw to draw our canvas.
    db.draw(canvas)

    return bit
}
