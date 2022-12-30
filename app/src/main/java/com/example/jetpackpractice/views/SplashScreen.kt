package com.example.jetpackpractice.views

import android.content.Context
import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.jetpackpractice.R
import com.example.jetpackpractice.navigation.NavigationRoute
import com.example.jetpackpractice.ui.theme.JetpackPracticeTheme
import com.example.jetpackpractice.utils.Preferences
import com.example.jetpackpractice.utils.utils
import com.example.jetpackpractice.utils.utils.ShowLog
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavController , context: Context) {

    JetpackPracticeTheme {
        val scale = remember {
            Animatable(0f)
        }
        // AnimationEffect
        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 800,
                    easing = {
                        OvershootInterpolator(10f).getInterpolation(it)
                    })
            )
                    "Email_From_DataStore==>".ShowLog(
                        Preferences().readString(
                            context,
                            utils.PREFERENCE_KEY_EMAIL
                        ))
                    "Password_From_DataStore==>".ShowLog(
                        Preferences().readString(context,
                            utils.PREFERENCE_KEY_PASSWORD
                        ))
                    "RememberMe_From_DataStore==>".ShowLog(
                        Preferences().readBoolean(context,
                            utils.PREFERENCE_KEY_REMEMBER_USER
                        ))

            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }
                // Get new FCM registration token
                val token = task.result
                Log.d("TAG", "FCM TOKEN ===> ${token}")
            })
            val currentUser: FirebaseUser? = FirebaseAuth.getInstance().getCurrentUser()
            if (currentUser == null) {
                // No user is signed in
                navController.navigate(NavigationRoute.Route.SigninScreen.route) {
                    // For Pop The Splash Screen Composable from the Navigation
                    popUpTo(NavigationRoute.Route.SplashScreen.route) {
                        inclusive = true
                    }
                }
            } else {
                // User logged in
                navController.navigate(NavigationRoute.Route.MainScreen.route) {
                    // For Pop The Splash Screen Composable from the Navigation
                    popUpTo(NavigationRoute.Route.SplashScreen.route) {
                        inclusive = true
                    }
                }
            }
        }

        // Image
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.white))
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_btc),
                contentDescription = "Logo",
                modifier = Modifier.scale(scale.value)
            )
        }
    }

}