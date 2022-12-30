package com.example.jetpackpractice.components

import android.os.Build
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackpractice.models.WatchlistData
import com.example.jetpackpractice.models.WatchlistModel
import com.example.jetpackpractice.R
import com.example.jetpackpractice.ui.theme.JetpackPracticeTheme

@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    JetpackPracticeTheme {
        AllCurrencies(Watchlist = WatchlistData.watchList)
    }
}

@Preview(showBackground = true)
@Composable
fun TopLayout() {
    JetpackPracticeTheme {
        TopLayout.TopLayout()
    }
}

@Composable
private fun AllCurrencies(Watchlist: List<WatchlistModel>) {

    Column {
//        Row(horizontalArrangement = Arrangement.End , modifier = Modifier.fillMaxWidth()) {
//            TopLayout.TopLayout()
//        }
        Text(
            text = "Welcome back, Balar",
            fontSize = 26.sp,
            color = MaterialTheme.colors.primary.copy(0.8f),
            modifier = Modifier.padding(start = 12.dp, top = 60.dp, bottom = 12.dp)
        )
        Card(
            backgroundColor = colorResource(id = R.color.white),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .border(
                    width = (0.5).dp,
                    color = colorResource(id = R.color.green),
                    shape = RoundedCornerShape(10.dp)
                ),
            elevation = 7.dp
        ) {
            Column() {
                Text(
                    text = "Your Portfolio Value",
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.primary.copy(0.7f),
                    modifier = Modifier.padding(start = 12.dp, top = 12.dp, end = 12.dp)
                )
                Text(
                    text = "₹1,50,132.40",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(start = 12.dp, top = 4.dp, end = 12.dp)
                )
                Text(
                    text = "+13.73% (₹20,613.17)",
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.green),
                    modifier = Modifier.padding(
                        start = 12.dp,
                        top = 4.dp,
                        end = 12.dp,
                        bottom = 12.dp
                    )
                )
            }
        }
        Text(
            text = "Your Watchlist",
            fontSize = 16.sp,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(start = 12.dp, end = 5.dp, top = 16.dp, bottom = 12.dp)
        )

        LazyColumn() {
            items(Watchlist) { modelData -> WatchList(modelData) }
        }
    }


}

@Composable
fun WatchList(watchlistModel: WatchlistModel) {
    // We keep track if the message is expanded or not in this
    // variable
//    var isExpanded by remember { mutableStateOf(false) }
    Column(
        //children
    ) {

        Surface(
            shape = RoundedCornerShape(12.dp),
            elevation = 3.dp,
            color = colorResource(id = R.color.white),
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
                .padding(
                    start = 10.dp,
                    top = 5.dp,
                    bottom = 5.dp,
                    end = 10.dp
                ),
        ) {

            Row(
                modifier = Modifier.padding(all = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = watchlistModel.icon),
                    contentDescription = "Android",
                    modifier = Modifier
                        .height(45.dp)
                        .width(45.dp)
                        .align(Alignment.CenterVertically)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column() {
                    Text(
                        text = watchlistModel.name,
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.padding(
                            start = 2.dp,
                            top = 2.dp,
                            bottom = 2.dp,
                            end = 2.dp
                        )
                    )
                    Text(
                        text = watchlistModel.currencyValue + " ₹",
                        fontSize = 14.sp,
                        color = MaterialTheme.colors.primary.copy(0.8f),
                        modifier = Modifier.padding(
                            start = 2.dp,
                            top = 2.dp,
                            bottom = 2.dp,
                            end = 2.dp
                        )
                    )
                }

                Row(horizontalArrangement = Arrangement.End) {
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = watchlistModel.growth + " %",
                        fontSize = 12.sp,
                        color = if (watchlistModel.growth.contains("-")) colorResource(id = R.color.red)
                        else colorResource(
                            id = R.color.green
                        ),
                        modifier = Modifier.padding(
                            start = 2.dp,
                            top = 2.dp,
                            bottom = 2.dp,
                            end = 2.dp
                        )
                    )
                }

            }
        }
    }
}

fun Modifier.dropColoredShadow(
    color: Color = Color.Green,
    alpha: Float = 0.2f,
    borderRadius: Dp = 0.dp,
    shadowRadius: Dp = 20.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
) = this.drawBehind {
    val transparentColor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        android.graphics.Color.toArgb(color.copy(alpha = 0.0f).value.toLong())
    } else {
        TODO("VERSION.SDK_INT < O")
    }
    val shadowColor = android.graphics.Color.toArgb(color.copy(alpha = alpha).value.toLong())
    this.drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparentColor
        frameworkPaint.setShadowLayer(
            shadowRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            shadowColor
        )
        it.drawRoundRect(
            0f,
            0f,
            this.size.width,
            this.size.height,
            borderRadius.toPx(),
            borderRadius.toPx(),
            paint
        )
    }
}
