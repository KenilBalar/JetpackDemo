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
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.example.jetpackpractice.navigation.NavigationRoute
import com.example.jetpackpractice.ui.theme.JetpackPracticeTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.math.sin

@Composable
fun ForgotPasswordScreen(
    navController: NavController, showLoader: MutableState<Boolean>, context: Context
) {
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

                val (constraintLayout, forgotPasswordText, emailTextField, sendResetLinkButton) = createRefs()

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
                    val emailID = remember { mutableStateOf("") }

                    Text(
                        text = stringResource(id = R.string.forgot_password),
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.dark_blue),
                        style = TextStyle(fontSize = 40.sp),
                        modifier = Modifier.constrainAs(forgotPasswordText) {
                            top.linkTo(parent.top, margin = 16.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                    )
                    Box(modifier = Modifier.constrainAs(emailTextField) {
                        top.linkTo(
                            forgotPasswordText.bottom,
                            margin = 50.dp
                        )
                    })
                    {
                        NormalTextFieldView(
                            lableName = stringResource(id = R.string.email_id),
                            valueName = emailID, type = EMAIL
                        )
                    }
                    Box(modifier = Modifier
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }) {}
                        .constrainAs(sendResetLinkButton) {
                            top.linkTo(
                                emailTextField.bottom,
                                margin = 30.dp
                            )

                            bottom.linkTo(parent.bottom, margin = 20.dp)
                        })
                    {
                        Button(
                            onClick = {
                                if (!emailID.value.isEmpty()) {
                                    showLoader.value = true
                                    onSendPasswordResetClick(
                                        context,
                                        email = emailID.value,
                                        navController = navController
                                    )
                                } else {
                                    Toast.makeText(
                                        context,
                                        R.string.enter_email,
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
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
                            Text(text = stringResource(id = R.string.send_reset_link))
                        }
                    }

                }
            }
        }
    }
}

private fun onSendPasswordResetClick(
    context: Context,
    auth: FirebaseAuth = Firebase.auth,
    email: String,
    navController: NavController
) {
    auth.sendPasswordResetEmail(email)
        .addOnCompleteListener() { task ->
            if (task.isSuccessful) {
                showLoader.value = false
                Log.e("Auth==", "SUCCESS")
                Toast.makeText(context, R.string.password_reset_link_sent, Toast.LENGTH_SHORT)
                    .show()
                navController.navigateAndReplaceStartRoute(NavigationRoute.Route.SigninScreen.route)
            } else {
                showLoader.value = false
                Log.e("Auth==", "FAILED")
                Log.e("Cause===", "${task.exception?.cause}")
                Log.e("Message===", "${task.exception?.message}")
                Toast.makeText(context, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
}