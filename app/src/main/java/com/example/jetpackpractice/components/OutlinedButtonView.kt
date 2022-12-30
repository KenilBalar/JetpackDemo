package com.example.jetpackpractice.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.jetpackpractice.R

@Composable
fun OutlinedButtonView(
    buttonText: String,
    onButtonClick: Unit,
    startPadding: Dp = 50.dp,
    endPadding: Dp = 50.dp,
    topPadding: Dp = 0.dp,
    bottomPadding: Dp = 0.dp,
) {
    Box(modifier = Modifier.padding(startPadding, topPadding, endPadding, bottomPadding)) {
        Button(
            onClick = { onButtonClick },
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            border = BorderStroke((0.5).dp, colorResource(id = R.color.dark_blue)),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.white),
                contentColor = colorResource(id = R.color.dark_blue)
            )
        ) {
            Text(text = buttonText)
        }
    }
}