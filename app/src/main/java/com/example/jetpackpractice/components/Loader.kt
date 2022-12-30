package com.example.jetpackpractice.components

import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.jetpackpractice.R
import com.example.jetpackpractice.navigation.NavigationRoute
import com.example.jetpackpractice.utils.utils
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Loader(showLoader  : MutableState<Boolean>) {
    val scale = remember {
        Animatable(1f)
    }
    Dialog(
        onDismissRequest = {},
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(Color.White.copy(1f) , shape = CircleShape)
        ) {
                    CircularProgressIndicator(color = colorResource(id = R.color.yellow) , strokeWidth = 2.dp,modifier = Modifier.padding(2.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_btc),
                contentDescription = "Logo",
                modifier = Modifier.scale(scale.value)
            )
        }
    }
}