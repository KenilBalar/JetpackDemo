package com.example.jetpackpractice.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackpractice.models.CurrencyData.currencyList
import com.example.jetpackpractice.models.CurrencyModel
import com.example.jetpackpractice.R
import com.example.jetpackpractice.ui.theme.JetpackPracticeTheme


@Preview(showBackground = true)
@Composable
fun MarketScreen() {
    JetpackPracticeTheme {
        Surface(modifier = Modifier.padding(bottom = 12.dp, top = 30.dp)) {
            Column() {
                Row(modifier = Modifier.padding(bottom = 12.dp)) {
                    Text(
                        text = "Market is",
                        fontSize = 18.sp,
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.padding(start = 12.dp, end = 5.dp)
                    )
                    Text(
                        text = "Down by 5.59%",
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.red)
                    )
                    Text(
                        text = "in last 24h",
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.primary.copy(0.7f),
                        modifier = Modifier
                            .align(Alignment.Bottom)
                            .padding(start = 5.dp)
                    )
                }
                LazyColumn() {
                    items(currencyList) { modelData -> MarketList(modelData) }
                }
            }
        }
    }
}

@Composable
fun MarketList(currencyModel: CurrencyModel) {
    // We keep track if the message is expanded or not in this
    // variable
//    var isExpanded by remember { mutableStateOf(false) }
    Column(
        //children
    ) {

        Surface(
            shape = RoundedCornerShape(12.dp),
            elevation =3.dp,
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
                    painter = painterResource(id = currencyModel.icon),
                    contentDescription = "Android",
                    modifier = Modifier
                        .height(45.dp)
                        .width(45.dp)
                        .align(Alignment.CenterVertically)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column() {
                    Text(
                        text = currencyModel.name,
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
                        text = currencyModel.currencyValue + " ₹",
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
                        text = currencyModel.growth + " %",
                        fontSize = 12.sp,
                        color = if (currencyModel.growth.contains("-")) colorResource(id = R.color.red)
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
