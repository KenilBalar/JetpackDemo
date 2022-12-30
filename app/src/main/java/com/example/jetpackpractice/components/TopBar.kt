package com.example.jetpackpractice.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.jetpackpractice.R

object TopBar {
    // A function which will receive a
// callback to trigger to opening the drawer
    @Composable
    fun TopBar(onMenuClicked: () -> Unit) {

        // TopAppBar Composable
        TopAppBar(
            // Provide Title
            title = {
                Text(text = "CryptTrack", color = colorResource(id = R.color.dark_blue))
            },
            // Provide the navigation Icon (Icon on the left to toggle drawer)
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",

                    // When clicked trigger onClick
                    // Callback to trigger drawer open
                    modifier = Modifier
                        .clickable(onClick = onMenuClicked)
                        .padding(start = 16.dp)
                        .dropColoredShadow(color = colorResource(id = R.color.dark_blue)),
                    tint = colorResource(id = R.color.dark_blue)
                )
            },
            // background color of topAppBar
            backgroundColor = colorResource(id = R.color.white)
        )
    }
}