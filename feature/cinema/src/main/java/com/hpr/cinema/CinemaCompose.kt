package com.hpr.cinema

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hpr.data.R
import com.hpr.data.model.cinema.BuyTicketRequest
import com.hpr.data.model.cinema.CinemaSeat
import com.hpr.data.model.cinema.SeatType
import com.hpr.data.model.cinema.WeekDay


@Composable
fun CinemaComposable() {
    ContainerUi()
}

@Composable
fun ContainerUi(
    modifier: Modifier = Modifier,
    viewModel: CinemaViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val selectedSeatList: MutableList<CinemaSeat> = remember {
        mutableStateListOf()
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    val ticketResult = viewModel.ticketResult.collectAsState("").value

    if (ticketResult.isNotEmpty()) {
        Toast.makeText(context, ticketResult, Toast.LENGTH_SHORT).show()
        isLoading = false
    }

    val seatDataList = viewModel.seatDataList
    Surface {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.Black)
        ) {

            Column(
                modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                IconButton(
                    onClick = {
                        (context as Activity).finish()
                    },
                    colors = IconButtonDefaults.iconButtonColors(containerColor = Color.DarkGray)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        tint = Color.White,
                        contentDescription = null
                    )
                }

                Image(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    painter = painterResource(id = R.drawable.projector),
                    contentDescription = null
                )

                /*Canvas(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    drawImage(
                        image = projectorImage,
                    )
                }*/

                LazyVerticalGrid(
                    modifier = modifier
                        .fillMaxWidth()
                        .heightIn(min = 150.dp, max = 500.dp)
                        .padding(top = 16.dp),
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(seatDataList) { seatData ->
                        SeatItem(seatData) { data ->
                            if (data.seatType == SeatType.Selected)
                                selectedSeatList.add(data)
                            else selectedSeatList.remove(data.copy(seatType = SeatType.Selected))
                        }
                    }
                }

                Row(
                    modifier = modifier
                        .padding(top = 32.dp, bottom = 32.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextWithIcon(
                        modifier = modifier,
                        text = SeatType.Available.name,
                        color = SeatType.Available.seatColor
                    )
                    TextWithIcon(
                        modifier = modifier,
                        text = SeatType.Taken.name,
                        color = SeatType.Taken.seatColor
                    )
                    TextWithIcon(
                        modifier = modifier,
                        text = SeatType.Selected.name,
                        color = SeatType.Selected.seatColor
                    )
                }
            }

            FooterCard(
                modifier = modifier,
                context = context,
                selectedSeatList = selectedSeatList,
                dayDataList = viewModel.dayDataList,
                hourDataList = viewModel.hourDataList,
                isLoading = isLoading
            ) {
                isLoading = true
                viewModel.buyTicket(it)
            }
        }
    }
}

@Composable
fun FooterCard(
    modifier: Modifier,
    context: Context,
    selectedSeatList: List<CinemaSeat>,
    dayDataList: List<WeekDay>,
    hourDataList : List<String>,
    isLoading : Boolean,
    onBuyTicketClick: (BuyTicketRequest) -> Unit
) {
    var totalPrice by remember {
        mutableLongStateOf(0L)
    }
    totalPrice = selectedSeatList.sumOf { it.price }
    var ticketCount by remember {
        mutableIntStateOf(0)
    }
    ticketCount = selectedSeatList.size

    var selectedDayIndex by remember {
        mutableIntStateOf(3)
    }
    var selectedHourIndex by remember {
        mutableIntStateOf(3)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(), shape = RoundedCornerShape(
            topStart = 16.dp, topEnd = 16.dp, bottomStart = 32.dp, bottomEnd = 32.dp
        ), colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(modifier = modifier.fillMaxWidth()) {
            LazyRow(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                repeat(dayDataList.size) {
                    item {
                        DayItem(
                            modifier = modifier,
                            weekDay = dayDataList[it],
                            selected = selectedDayIndex == it
                        ) {
                            selectedDayIndex = it
                        }
                    }
                }
            }

            LazyRow(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                itemsIndexed(hourDataList, key = { _, item ->
                    item
                }) { index, data ->
                    HourItem(
                        modifier = modifier,
                        hour = data,
                        selected = selectedHourIndex == index
                    ) {
                        selectedHourIndex = index
                    }
                }
            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Column(
                    modifier = modifier.weight(1f)
                ) {
                    Text(
                        text = "$$totalPrice",
                        fontSize = TextUnit(18f, TextUnitType.Sp),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        modifier = modifier.padding(top = 4.dp),
                        text = "$ticketCount ticket",
                        fontSize = TextUnit(12f, TextUnitType.Sp),
                        fontWeight = FontWeight.Light,
                        color = Color.Gray
                    )
                }

                Button(
                    modifier = modifier
                        .weight(1f)
                        .heightIn(min = 50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SeatType.Selected.seatColor
                    ),
                    onClick = {
                        validation(selectedSeatList, context) {
                            onBuyTicketClick(
                                BuyTicketRequest(
                                    seatId = selectedSeatList.joinToString { it.seatId.toString() },
                                    day = dayDataList[selectedDayIndex].dateDayNum.toString(),
                                    hour = hourDataList[selectedHourIndex]
                                )
                            )
                        }
                    })
                {

                    if (isLoading)
                        CircularProgressIndicator(
                            modifier = modifier.size(16.dp),
                            color = Color.White
                        )
                    else {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_card),
                            contentDescription = null
                        )
                    }

                    Text(
                        modifier = modifier.padding(start = 8.dp),
                        text = "Buy tickets",
                        fontSize = TextUnit(16f, TextUnitType.Sp),
                        fontWeight = FontWeight.Normal,

                        )

                }
            }


        }
    }
}

@Composable
fun TextWithIcon(
    modifier: Modifier,
    text: String,
    color: Color,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = modifier
                .padding(end = 4.dp)
                .size(12.dp)
                .clip(CircleShape)
                .background(color)
        )
        Text(
            text = text,
            fontSize = TextUnit(14f, TextUnitType.Sp),
            color = Color(0xff656565)
        )
    }
}

fun validation(selectedSeatList: List<CinemaSeat>, context: Context, isValid: () -> Unit) {
    if (selectedSeatList.isNotEmpty()) {
        isValid.invoke()
    } else {
        Toast.makeText(context, "Select a seat please!", Toast.LENGTH_SHORT).show()
    }
}

@Preview
@Composable
fun Preview() {
    ContainerUi()
}