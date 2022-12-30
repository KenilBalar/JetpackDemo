package com.example.jetpackpractice.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object TopLayout {

    @Composable
    fun TopLayout() {

        Row() {

            Surface(
                shape = RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 0.dp,
                    bottomStart = 30.dp,
                    bottomEnd = 0.dp
                ),
                elevation = 3.dp,
                color = MaterialTheme.colors.primary
            ) {
                Row(
                    modifier = Modifier.padding(all = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "Jetpack Practice",
                        fontSize = 26.sp,
                        color = MaterialTheme.colors.secondary,
                        modifier = Modifier.padding(
                            start = 2.dp,
                            top = 2.dp,
                            bottom = 2.dp,
                            end = 2.dp
                        ),
                    )
                }
            }
        }
    }

}
