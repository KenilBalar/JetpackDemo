package com.example.jetpackpractice.components

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.jetpackpractice.R
import com.example.jetpackpractice.navigation.NavigationRoute

val showDialog: MutableState<Boolean> = mutableStateOf(false)
var state: State<Boolean> = showDialog

@Composable
fun Drawer(navController: NavController, context: Context, showDrawer : MutableState<Boolean>, navController1: NavController) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white).copy(0f))
    ) {
        val (mainMenuText, logoutButton, mainScreen, marketScreen, notificationScreen) = createRefs()

        Box(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(mainMenuText) {
                top.linkTo(parent.top, margin = 50.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        {
            Row(modifier = Modifier.padding(start = 40.dp), verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = stringResource(id = R.string.main_menu),
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.dark_blue),
                    style = TextStyle(fontSize = 30.sp)
                )
            }
        }

        Box(contentAlignment = Alignment.CenterStart,modifier = Modifier.constrainAs(mainScreen) {
            top.linkTo(mainMenuText.bottom, margin = 50.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end, margin = 40.dp)

        })
        {
            Button(
                onClick = {
                    showDrawer.value = true
                    if (navController1.currentDestination!!.route!! != NavigationRoute.BottomNavigationRoute.Home.screen_route){
                        navController1.navigate(NavigationRoute.BottomNavigationRoute.Home.screen_route){
                            popUpTo(NavigationRoute.BottomNavigationRoute.Home.screen_route){
                                inclusive = false
                            }
                        }
                    }
                },
                elevation = ButtonDefaults.elevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 40.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 40.dp
                ),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.white) , contentColor = colorResource(id = R.color.dark_blue)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .offset(x = (-1).dp)
                    .dropColoredShadow(
                        colorResource(id = R.color.black),
                        shadowRadius = 2.dp,
                        alpha = 0.4f,
                        borderRadius = 40.dp,
                        offsetX = 1.dp,
                        offsetY = 1.dp
                    )
            ) {
                Row(modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 40.dp), verticalAlignment = Alignment.CenterVertically){
                    Image(
                        painter = painterResource(id = R.drawable.ic_home),
                        contentDescription = "Main Screen",
                        colorFilter = ColorFilter.tint(
                            colorResource(id = R.color.dark_blue)
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(id = R.string.home))
                }
            }
        }

        Box(modifier = Modifier.constrainAs(marketScreen) {
            top.linkTo(mainScreen.bottom, margin = 10.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end, margin = 40.dp)

        })
        {
            Button(
                onClick = {
                    showDrawer.value = true
                    if (navController1.currentDestination!!.route!! != NavigationRoute.BottomNavigationRoute.Market.screen_route) {
                        navController1.navigate(NavigationRoute.BottomNavigationRoute.Market.screen_route){
                            popUpTo(NavigationRoute.BottomNavigationRoute.Market.screen_route){
                                inclusive = false
                            }
                        }
                    }
                },
                elevation = ButtonDefaults.elevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 40.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 40.dp
                ),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.white) , contentColor = colorResource(id = R.color.dark_blue)),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
                    .height(50.dp)
                    .offset(x = (-1).dp)
                    .dropColoredShadow(
                        colorResource(id = R.color.black),
                        shadowRadius = 2.dp,
                        alpha = 0.4f,
                        borderRadius = 40.dp,
                        offsetX = 1.dp,
                        offsetY = 1.dp
                    )
            ) {
                Row(modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 40.dp), verticalAlignment = Alignment.CenterVertically){
                    Image(
                        painter = painterResource(id = R.drawable.ic_portfolio),
                        contentDescription = "Main Screen",
                        colorFilter = ColorFilter.tint(
                            colorResource(id = R.color.dark_blue)
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(id = R.string.market))
                }

            }
        }

        Box(modifier = Modifier.constrainAs(notificationScreen) {
            top.linkTo(marketScreen.bottom, margin = 10.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end, margin = 40.dp)

        })
        {
            Button(
                onClick = {
                    showDrawer.value = true
                    if (navController1.currentDestination!!.route!! != NavigationRoute.BottomNavigationRoute.Notification.screen_route) {
                        navController1.navigate(NavigationRoute.BottomNavigationRoute.Notification.screen_route){
                            popUpTo(NavigationRoute.BottomNavigationRoute.Notification.screen_route){
                                inclusive = false
                            }
                        }
                    }
                },
                elevation = ButtonDefaults.elevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 40.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 40.dp
                ),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.white) , contentColor = colorResource(id = R.color.dark_blue)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .offset(x = (-1).dp)
                    .dropColoredShadow(
                        colorResource(id = R.color.black),
                        shadowRadius = 2.dp,
                        alpha = 0.4f,
                        borderRadius = 40.dp,
                        offsetX = 1.dp,
                        offsetY = 1.dp
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 40.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_notification),
                        contentDescription = "Notification Screen",
                        colorFilter = ColorFilter.tint(
                            colorResource(id = R.color.dark_blue)
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(id = R.string.notification))
                }
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(logoutButton) {
                bottom.linkTo(parent.bottom, margin = 40.dp)
                end.linkTo(parent.end)
            },
            contentAlignment = Alignment.CenterEnd)
        {
            Button(
                onClick = {
                    showDialog.value = true
                    showDrawer.value = true
                },
                elevation = ButtonDefaults.elevation(defaultElevation = 5.dp),
                shape = RoundedCornerShape(
                    topStart = 40.dp,
                    topEnd = 0.dp,
                    bottomStart = 40.dp,
                    bottomEnd = 0.dp
                ),
                modifier = Modifier
                    .height(50.dp)
                    .offset(x = (1).dp)
                    .dropColoredShadow(
                        colorResource(id = R.color.black),
                        shadowRadius = 2.dp,
                        alpha = 0.4f,
                        borderRadius = 40.dp,
                        offsetX = (-2).dp,
                        offsetY = 1.dp
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logout),
                    contentDescription = "logout",
                    colorFilter = ColorFilter.tint(
                        colorResource(id = R.color.white)
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(id = R.string.logout))
            }
        }
    }
    if (state.value) {
        AlertDialogClass(showdialog = showDialog, context = context, navController)
    }
}

