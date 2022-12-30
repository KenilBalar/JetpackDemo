package com.example.jetpackpractice.viewModels

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.jetpackpractice.R
import com.example.jetpackpractice.models.LoginModel
import com.example.jetpackpractice.navigation.NavigationRoute
import com.example.jetpackpractice.utils.Preferences
import com.example.jetpackpractice.utils.utils
import com.example.jetpackpractice.utils.utils.PREFERENCE_KEY_EMAIL
import com.example.jetpackpractice.utils.utils.PREFERENCE_KEY_PASSWORD
import com.example.jetpackpractice.utils.utils.PREFERENCE_KEY_REMEMBER_USER
import com.example.jetpackpractice.utils.utils.ShowLog
import com.example.jetpackpractice.views.showLoader
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(application: Application) : AndroidViewModel(application) {

    val isTouched: MutableState<Boolean> = mutableStateOf(false)
    val checkedState: MutableState<Boolean> = mutableStateOf(false)
    val state: State<Boolean> = checkedState
    val emailID: MutableState<String> = mutableStateOf("")
    val password: MutableState<String> = mutableStateOf("")

    init {
        viewModelScope.launch {

            if (Preferences().readBoolean(
                    getApplication(),
                    PREFERENCE_KEY_REMEMBER_USER
                )
            ) {
                emailID.value =
                    Preferences().readString(getApplication(), PREFERENCE_KEY_EMAIL)
                password.value =
                    Preferences().readString(getApplication(), PREFERENCE_KEY_PASSWORD)
                checkedState.value =
                    Preferences().readBoolean(getApplication(), PREFERENCE_KEY_REMEMBER_USER)
            }

        }
    }

    fun OnRememberMeChecked(view: View) {
    }

    fun OnSigninClick(
        navController: NavController,
        showLoader: MutableState<Boolean>
    ) {
        if (!emailID.value.isEmpty() && !password.value.isEmpty()) {

            Firebase.auth.signInWithEmailAndPassword(emailID.value, password.value)
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        showLoader.value = false
                        Log.e("Auth==", "SUCCESS")
                        Toast.makeText(
                            getApplication(),
                            R.string.logged_in_successfully,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        navController.navigate(NavigationRoute.Route.MainScreen.route) {
                            popUpTo(NavigationRoute.Route.SigninScreen.route) {
                                inclusive = true
                            }
                            if (checkedState.value) {
                                viewModelScope.launch {
                                    Preferences().writeString(
                                        getApplication(),
                                        PREFERENCE_KEY_EMAIL,
                                        emailID.value
                                    )
                                    Preferences().writeString(
                                        getApplication(),
                                        PREFERENCE_KEY_PASSWORD,
                                        password.value
                                    )
                                    Preferences().writeBoolean(
                                        getApplication(),
                                        PREFERENCE_KEY_REMEMBER_USER, checkedState.value
                                    )

                                    "Email_From_DataStore==>".ShowLog(
                                        Preferences().readString(
                                            getApplication(),
                                            PREFERENCE_KEY_EMAIL
                                        )
                                    )
                                    "Password_From_DataStore==>".ShowLog(
                                        Preferences().readString(
                                            getApplication(),
                                            PREFERENCE_KEY_PASSWORD
                                        )
                                    )
                                    "RememberMe_From_DataStore==>".ShowLog(
                                        Preferences().readBoolean(
                                            getApplication(),
                                            PREFERENCE_KEY_REMEMBER_USER
                                        )
                                    )
                                }
                            }
                            else
                            {
                                viewModelScope.launch {
                                Preferences().writeBoolean(
                                    getApplication(),
                                    PREFERENCE_KEY_REMEMBER_USER, checkedState.value
                                )
                                }
                                emailID.value = ""
                                password.value = ""
                                checkedState.value = false
                            }
                        }
                    } else {
                        showLoader.value = false
                        Log.e("Auth==", "FAILED")
                        Log.e("Cause===", "${task.exception?.cause}")
                        Log.e("Message===", "${task.exception?.message}")
                        Toast.makeText(
                            getApplication(),
                            "${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
        } else {
            if (emailID.value.isEmpty()) {
                Toast.makeText(
                    getApplication(),
                    R.string.enter_email,
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    getApplication(),
                    R.string.enter_password,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun OnSignupClick(navController: NavController) {
        navController.navigate(NavigationRoute.Route.SignupScreen.route) {

            // For Pop The Signin Screen Composable from the Navigation
            popUpTo(NavigationRoute.Route.SigninScreen.route) {
                inclusive = true
            }
        }
    }
}

