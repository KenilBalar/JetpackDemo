package com.example.jetpackpractice.views

import android.content.Context
import android.util.Log
import android.view.MotionEvent
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.RequestDisallowInterceptTouchEvent
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.jetpackpractice.R
import com.example.jetpackpractice.components.*
import com.example.jetpackpractice.navigation.Constants.EMAIL
import com.example.jetpackpractice.navigation.Constants.PASSWORD
import com.example.jetpackpractice.navigation.NavigationRoute
import com.example.jetpackpractice.ui.theme.JetpackPracticeTheme
import com.example.jetpackpractice.utils.Preferences
import com.example.jetpackpractice.utils.utils
import com.example.jetpackpractice.viewModels.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.coroutineScope
import kotlin.math.sin

@ExperimentalComposeUiApi
@Composable
fun SigninScreen(
    navController: NavController, showLoader: MutableState<Boolean>,loginViewModel: LoginViewModel, context: Context
) {
    val isTouched = remember { mutableStateOf(false) }
    JetpackPracticeTheme {

        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = colorResource(id = R.color.white)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                val (constraintLayout, signInText, emailTextField, passwordTextField, signInButton, signUpButton,rememberMeCheckBox, forgotPasswordText) = createRefs()

                ConstraintLayout(modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .verticalScroll(rememberScrollState())
                    .constrainAs(constraintLayout) {
                        top.linkTo(parent.top, margin = 20.dp)
                        start.linkTo(parent.start, margin = 20.dp)
                        end.linkTo(parent.end, margin = 20.dp)
                        bottom.linkTo(parent.bottom, margin = 20.dp)
                    })
                {

                    Text(
                        text = stringResource(id = R.string.login),
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.dark_blue),
                        style = TextStyle(fontSize = 40.sp),
                        modifier = Modifier.constrainAs(signInText) {
                            top.linkTo(parent.top, margin = 16.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                    )
                    Box(modifier = Modifier.constrainAs(emailTextField) {
                        top.linkTo(
                            signInText.bottom,
                            margin = 50.dp
                        )
                    })
                    {
                        NormalTextFieldView(
                            lableName = stringResource(id = R.string.email_id),
                            loginViewModel.emailID, EMAIL
                        )
                    }
                    Box(modifier = Modifier.constrainAs(passwordTextField) {
                        top.linkTo(
                            emailTextField.bottom,
                            margin = 10.dp
                        )
                    })
                    {
                        NormalTextFieldView(
                            lableName = stringResource(id = R.string.password),
                            loginViewModel.password,PASSWORD
                        )
                    }
                    Box(modifier = Modifier.constrainAs(rememberMeCheckBox) {
                        top.linkTo(
                            passwordTextField.bottom, margin = 10.dp
                        )
                        start.linkTo(parent.start)
                    }
                    ){
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = loginViewModel.checkedState.value,
                                onCheckedChange = { loginViewModel.checkedState.value = it },
                                colors = CheckboxDefaults.colors(checkedColor = colorResource(id = R.color.dark_blue), uncheckedColor = colorResource(id = R.color.dark_blue))
                            )
                            Text(
                                text = stringResource(id = R.string.remember_me),
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily.Default,
                                    color = colorResource(id = R.color.dark_blue)
                                )
                            )
                        }
                    }
                    Box(modifier = Modifier.constrainAs(forgotPasswordText) {
                        top.linkTo(rememberMeCheckBox.top)
                        bottom.linkTo(rememberMeCheckBox.bottom)
                        end.linkTo(parent.end)
                    }
                    )
                    {
                        Text(
                            text = stringResource(id = R.string.forgot_password),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Default,
                                color = if (isTouched.value) colorResource(id = R.color.dark_blue).copy(
                                    0.4f
                                ) else colorResource(id = R.color.dark_blue).copy(1f)
                            ),
                            modifier = Modifier
                                .clickable{ navController.navigate(NavigationRoute.Route.ForgotPasswordScreen.route) }
                        )
                    }
                    Box(modifier = Modifier
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }) {}
                        .constrainAs(signInButton) {
                            top.linkTo(
                                forgotPasswordText.bottom,
                                margin = 30.dp
                            )
                        })
                    {
                        Button(
                            onClick = {
                                    showLoader.value = true
                                    loginViewModel.OnSigninClick(
                                        navController = navController,
                                        showLoader = showLoader
                                    )
                            },
                            border = BorderStroke((0.5).dp, colorResource(id = R.color.dark_blue)),
                            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }) {}
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(text = stringResource(id = R.string.signin))
                        }
                    }
                    Box(modifier = Modifier.constrainAs(signUpButton) {
                        top.linkTo(
                            signInButton.bottom,
                            margin = 10.dp
                        )
                        bottom.linkTo(parent.bottom, margin = 20.dp)
                    })
                    {
                        Button(
                            onClick = {
                                loginViewModel.OnSignupClick(navController = navController)
                            },
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
                            border = BorderStroke((0.5).dp, colorResource(id = R.color.dark_blue)),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.White, contentColor = colorResource(
                                    id = R.color.dark_blue
                                )
                            ),
                        ) {
                            Text(text = stringResource(id = R.string.signup))
                        }
                    }
                }
            }
        }


    }
//if (stateLoader.value){
//    Loader()
//}

//    if(loginViewModel.state.value)
//    {
//        loginViewModel
//    }
}