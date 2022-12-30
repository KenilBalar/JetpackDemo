package com.example.jetpackpractice.views

import android.annotation.SuppressLint
import android.content.Context
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jetpackpractice.R
import com.example.jetpackpractice.components.BottomBar
import com.example.jetpackpractice.components.Drawer
import com.example.jetpackpractice.components.TopBar
import com.example.jetpackpractice.navigation.Navigation
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController1: NavController,showLoader  : MutableState<Boolean>, context: Context)
{
    ScaffoldExample(navController1)
}

val showDrawer  : MutableState<Boolean> = mutableStateOf(false)
var drawerState : State<Boolean> = showDrawer

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ScaffoldExample(navController1: NavController){

    // create a scaffold state, set it to close by default
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    // Create a coroutine scope. Opening of
    // Drawer and snackbar should happen in
    // background thread without blocking main thread
    val coroutineScope = rememberCoroutineScope()

    val navController = rememberNavController()

    // Scaffold Composable
    Scaffold(
        // pass the scaffold state
        scaffoldState = scaffoldState,
        // pass the topbar we created
        topBar = {
            TopBar.TopBar(
                // When menu is clicked open the
                // drawer in coroutine scope
                onMenuClicked = {
                    coroutineScope.launch {
                        // to close use -> scaffoldState.drawerState.close()
                        scaffoldState.drawerState.open()
                    }
                })
        },
        // pass the bottomBar
        // we created
        bottomBar = { BottomBar.BottomBar(navController = navController) },
        // pass the drawer
        drawerContent = {
            Drawer(navController1, LocalContext.current, showDrawer, navController)
        },
        drawerScrimColor = colorResource(id = R.color.black).copy(0.8f),
        drawerGesturesEnabled = true,
        drawerContentColor = MaterialTheme.colors.primary,
    ) { innerPadding ->
        // Apply the padding globally to the whole BottomNavScreensController
        Box(modifier = Modifier.padding(innerPadding)) {
            Navigation.BottomNavigationGraph(navController = navController)
        }
    }

    if (drawerState.value){
        coroutineScope.launch {
            // to close use -> scaffoldState.drawerState.close()
            scaffoldState.drawerState.close()
            showDrawer.value = false
        }
    }
}

