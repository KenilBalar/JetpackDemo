package com.example.jetpackpractice.views

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.jetpackpractice.R
import com.example.jetpackpractice.components.*
import com.example.jetpackpractice.navigation.Constants.EMAIL
import com.example.jetpackpractice.navigation.Constants.PASSWORD
import com.example.jetpackpractice.navigation.Constants.USER
import com.example.jetpackpractice.navigation.NavigationRoute
import com.example.jetpackpractice.ui.theme.JetpackPracticeTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun SignupScreen(/*popUpScreen: () -> Unit, viewModel: LoginViewModel = hiltViewModel(),*/
    navController: NavController,showLoader  : MutableState<Boolean>, context: Context
) {
    var context = LocalContext.current
    JetpackPracticeTheme {

        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = colorResource(id = R.color.white)
        ) {

            ConstraintLayout(modifier = Modifier
                .fillMaxSize()
            ) {

                val (constraintLayout, registerText, usernameTextField,emailTextField, passwordTextField,confirmPasswordTextField, signInButton, signUpButton) = createRefs()

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
                    // Create references for the composables to constrain

                    val username = remember { mutableStateOf("") }
                    val emailID = remember { mutableStateOf("") }
                    val password = remember { mutableStateOf("") }
                    val confirmPassword = remember { mutableStateOf("") }
                    Text(
                        text = stringResource(id = R.string.register),
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.dark_blue),
                        style = TextStyle(fontSize = 40.sp),
                        modifier = Modifier.constrainAs(registerText) {
                            top.linkTo(parent.top, margin = 16.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                    )
                    Box(modifier = Modifier.constrainAs(usernameTextField) {
                        top.linkTo(
                            registerText.bottom,
                            margin = 50.dp
                        )
                    })
                    {
                        NormalTextFieldView(
                            lableName = stringResource(id = R.string.username),
                        username ,USER)
                    }
                    Box(modifier = Modifier.constrainAs(emailTextField) {
                        top.linkTo(
                            usernameTextField.bottom,
                            margin = 10.dp
                        )
                    })
                    {
                        NormalTextFieldView(
                            lableName = stringResource(id = R.string.email_id),
                            emailID, EMAIL
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
                            password, PASSWORD
                        )
                    }
                    Box(modifier = Modifier.constrainAs(confirmPasswordTextField) {
                        top.linkTo(
                            passwordTextField.bottom,
                            margin = 10.dp
                        )
                    })
                    {
                        NormalTextFieldView(
                            lableName = stringResource(id = R.string.confirm_password),
                            confirmPassword, PASSWORD
                        )
                    }
                    Box(modifier = Modifier.constrainAs(signUpButton) {
                        top.linkTo(
                            confirmPasswordTextField.bottom,
                            margin = 30.dp
                        )
                    })
                    {
                        Button(
                            onClick = {
                                if (!emailID.value.isEmpty() && !password.value.isEmpty()) {
                                    showLoader.value = true
                                    onSignupClick(
                                        context,
                                        email = emailID.value,
                                        password = password.value,
                                        navController = navController,
                                        showLoader = showLoader
                                    )
                                } else {
                                    if (emailID.value.isEmpty()) {
                                        Toast.makeText(
                                            context,
                                            R.string.enter_email,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        Toast.makeText(
                                            context,
                                            R.string.enter_password,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            },
                            border = BorderStroke((0.5).dp, colorResource(id = R.color.dark_blue)),
                            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(text = stringResource(id = R.string.signup))
                        }
                    }
                    Box(modifier = Modifier.constrainAs(signInButton) {
                        top.linkTo(
                            signUpButton.bottom,
                            margin = 10.dp
                        )

                        bottom.linkTo(parent.bottom, margin = 20.dp)
                    })
                    {
                        Button(
                            onClick = {
                                navController.navigate(NavigationRoute.Route.SigninScreen.route) {

                                    // For Pop The Signin Screen Composable from the Navigation
                                    popUpTo(NavigationRoute.Route.SignupScreen.route) {
                                        inclusive = true
                                    }
                                }
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
                            Text(text = stringResource(id = R.string.signin))
                        }

                    }
                }
            }
        }
    }
}

private fun onSignupClick(
    context: Context,
    auth: FirebaseAuth = Firebase.auth,
    email: String,
    password: String,
    navController: NavController,
    showLoader  : MutableState<Boolean>
) {

    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener() { task ->
            if (task.isSuccessful) {
                Log.e("Auth==", "SUCCESS")
                navController.navigate(NavigationRoute.Route.SigninScreen.route)
                showLoader.value = false
                Toast.makeText(context, R.string.registered_successfully, Toast.LENGTH_SHORT).show()
            } else {
                showLoader.value = false
                Log.e("Auth==", "FAILED")
                Log.e("Cause===", "${task.exception?.cause}")
                Log.e("Message===", "${task.exception?.message}")
                Toast.makeText(context, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
        .addOnFailureListener() {
            Log.e("Message===", "${it.message}")
        }
}
