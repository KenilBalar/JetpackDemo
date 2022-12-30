package com.example.jetpackpractice

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.jetpackpractice.components.HomeScreen
import com.example.jetpackpractice.components.MarketScreen
import com.example.jetpackpractice.components.NotificationScreen
import com.example.jetpackpractice.views.SplashScreen
import com.example.jetpackpractice.ui.theme.JetpackPracticeTheme
import com.example.jetpackpractice.views.SigninScreen
import com.example.jetpackpractice.views.SignupScreen

class AllScreenPreview {

    @Preview
    @Composable
    fun Splash() {
        JetpackPracticeTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                val navController = rememberNavController()
//                SplashScreen(navController)
            }
        }
    }
    @ExperimentalComposeUiApi
    @Preview
    @Composable
    fun SignIn() {
        JetpackPracticeTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                val navController = rememberNavController()
//                SigninScreen(navController , showLoader = remember { mutableStateOf(false) } )
            }
        }
    }

    @Preview
    @Composable
    fun SignUp() {
        JetpackPracticeTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                val navController = rememberNavController()
//                SignupScreen(navController, showLoader = remember { mutableStateOf(false) } )
            }
        }
    }

    @Preview
    @Composable
    fun Home() {
        JetpackPracticeTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                HomeScreen()
            }
        }
    }

    @Preview
    @Composable
    fun Market() {
        JetpackPracticeTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                MarketScreen()
            }
        }
    }

    @Preview
    @Composable
    fun Notification() {
        JetpackPracticeTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                NotificationScreen()
            }
        }
    }
}