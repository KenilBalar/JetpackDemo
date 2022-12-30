package com.example.jetpackpractice.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpackpractice.components.HomeScreen
import com.example.jetpackpractice.components.MarketScreen
import com.example.jetpackpractice.components.NotificationScreen
import com.example.jetpackpractice.viewModels.LoginViewModel
import com.example.jetpackpractice.views.*

object Navigation {
    @Composable
    fun BottomNavigationGraph(navController: NavHostController) {
        NavHost(
            navController,
            startDestination = NavigationRoute.BottomNavigationRoute.Home.screen_route
        ) {
            composable(NavigationRoute.BottomNavigationRoute.Home.screen_route) {
                HomeScreen()
            }
            composable(NavigationRoute.BottomNavigationRoute.Market.screen_route) {
                MarketScreen()
            }
            composable(NavigationRoute.BottomNavigationRoute.Notification.screen_route) {
                NotificationScreen()
            }
        }
    }

    @ExperimentalComposeUiApi
    @Composable
    fun NavigationGraph(navController: NavHostController , showLoader: MutableState<Boolean> ,context: Context, loginViewModel: LoginViewModel) {
        NavHost(
            navController,
            startDestination = NavigationRoute.Route.SplashScreen.route
        ) {
            composable(NavigationRoute.Route.SplashScreen.route) {
                SplashScreen(navController,context)
            }
            composable(NavigationRoute.Route.MainScreen.route) {
                MainScreen(navController,showLoader,context)
            }
            composable(NavigationRoute.Route.SigninScreen.route) {
                SigninScreen(navController,showLoader,loginViewModel,context)
            }
            composable(NavigationRoute.Route.SignupScreen.route) {
                SignupScreen(navController,showLoader,context)
            }
            composable(NavigationRoute.Route.ForgotPasswordScreen.route) {
                ForgotPasswordScreen(navController,showLoader,context)
            }
        }
    }
}