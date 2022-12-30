package com.example.jetpackpractice.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.jetpackpractice.R
import com.example.jetpackpractice.navigation.NavigationRoute

@Composable
fun ShadowButtonView(
    buttonText: String,
    shadowColor: Color = colorResource(
        id = R.color.black
    ),
    startPadding : Dp = 50.dp,
    endPadding : Dp = 50.dp,
    topPadding : Dp = 0.dp,
    bottomPadding : Dp = 0.dp,
    shadowRadius_ : Dp = 15.dp,
    borderRadius_ : Dp = 40.dp,
    onButtonClick: Unit
) {
    Box(modifier = Modifier.padding(startPadding, topPadding, endPadding, bottomPadding)) {
        Button(
            onClick = { onButtonClick },
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .dropColoredShadow(
                    color = shadowColor,
                    offsetX = 0.dp,
                    offsetY = 0.dp,
                    shadowRadius = shadowRadius_,
                    borderRadius = borderRadius_
                )
        ) {
            Text(text = buttonText)
        }
    }
}