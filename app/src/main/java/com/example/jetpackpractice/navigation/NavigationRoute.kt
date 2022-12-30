package com.example.jetpackpractice.navigation

import com.example.jetpackpractice.R

object NavigationRoute {
    sealed class BottomNavigationRoute(var title:String, var icon:Int, var screen_route:String){

        object Home : BottomNavigationRoute("Home", R.drawable.ic_home,Constants.HOME_SCREEN)
        object Market: BottomNavigationRoute("Market", R.drawable.ic_portfolio,Constants.MARKET_SCREEN)
        object Notification: BottomNavigationRoute("Notification", R.drawable.ic_notification,Constants.NOTIFICATION_SCREEN)
    }

    sealed class Route(var route:String){

        object SplashScreen : Route(Constants.SPLASH_SCREEN)
        object MainScreen : Route(Constants.MAIN_SCREEN)
        object SigninScreen: Route(Constants.SIGNIN_SCREEN)
        object ForgotPasswordScreen: Route(Constants.FORGOT_PASSWORD_SCREEN)
        object SignupScreen: Route(Constants.SIGNUP_SCREEN)
    }
}