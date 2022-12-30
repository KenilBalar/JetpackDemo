package com.example.jetpackpractice.views

import android.app.Application
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.compose.rememberNavController
import com.example.jetpackpractice.R
import com.example.jetpackpractice.components.Loader
import com.example.jetpackpractice.navigation.Navigation
import com.example.jetpackpractice.ui.theme.JetpackPracticeTheme
import com.example.jetpackpractice.viewModels.LoginViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

val showLoader: MutableState<Boolean> = mutableStateOf(false)
var stateLoader: State<Boolean> = showLoader

@AndroidEntryPoint
class DemoActivity : ComponentActivity() {

    private  val loginViewModel : LoginViewModel by viewModels()

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            JetpackPracticeTheme {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = Color(resources.getColor(R.color.white)),
                        darkIcons = true
                    )
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.dark_blue1)

                ) {
                    val navController = rememberNavController()
//                    if (intent.hasExtra("isLogout")) {
//                        Navigation.NavigationGraph(navController = navController, isLogout = true)
//                    } else {
                        Navigation.NavigationGraph(navController = navController , showLoader,this,
                            loginViewModel!!
                        )
//                    }
                    isLoading()
                }
            }

        }
    }
}

@Composable
fun isLoading()
{
    if (stateLoader.value) {
        Loader(showLoader = showLoader)
    }
}

@Preview
@Composable
fun ShowPreview() {
    JetpackPracticeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
//            ScaffoldExample()
        }
    }
}