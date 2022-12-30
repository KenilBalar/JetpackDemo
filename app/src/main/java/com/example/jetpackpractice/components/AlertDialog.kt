package com.example.jetpackpractice.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.jetpackpractice.R
import com.example.jetpackpractice.navigation.NavigationRoute
import com.example.jetpackpractice.utils.utils.SHOW_DIALOG
import com.google.firebase.auth.FirebaseAuth


@Composable
fun AlertDialogClass(showdialog : MutableState<Boolean>,
    context: Context,navController: NavController,
) {
    MaterialTheme {
        Column {
            val openDialog = remember { mutableStateOf(SHOW_DIALOG) }
//            if (openDialog.value.equals(SHOW_DIALOG)) {

                AlertDialog(
                    shape = RoundedCornerShape(12.dp),
                    onDismissRequest = {
                        // Dismiss the dialog when the user clicks outside the dialog or on the back
                        // button. If you want to disable that functionality, simply use an empty
                        // onCloseRequest.
//                        openDialog.value = HIDE_DIALOG
//                        DrawerClass().showdialog.value = false
                        showdialog.value = false
                    },
                    title = {
                        Text(text = stringResource(id = R.string.logout))
                    },
                    text = {
                        Text("Are you sure want to logout?")
                    },
                    confirmButton = {
                        Button(
                            onClick = {
//                                openDialog.value = LOGOUT
                                showdialog.value = false
                                FirebaseAuth.getInstance().signOut()
                                Toast.makeText(context, R.string.logged_out, Toast.LENGTH_SHORT).show()
                                navController.navigateAndReplaceStartRoute(NavigationRoute.Route.SigninScreen.route)
                            }) {
                            Text("Logout")
                        }
                    },
                    properties = DialogProperties(dismissOnBackPress = true,dismissOnClickOutside = true),
                    dismissButton = {
                        Button(

                            onClick = {
//                                openDialog.value = HIDE_DIALOG
                                showdialog.value = false
                            }, colors = ButtonDefaults.buttonColors(
                                backgroundColor = colorResource(
                                    id = R.color.white
                                ),
                                contentColor = colorResource(id = R.color.dark_blue)
                            )
                        ) {
                            Text("Cancel")
                        }
                    }
                )
//            }

//            if (openDialog.value.equals(LOGOUT)) {
//                var context = LocalContext.current
//                FirebaseAuth.getInstance().signOut()
//                Toast.makeText(context, R.string.logged_out, Toast.LENGTH_SHORT).show()
//                Navigation.NavigationGraph(navController = navController, isLogout = true)
//            }

        }
    }
}

fun NavController.navigateAndReplaceStartRoute(newHomeRoute: String) {
//    popBackStack(graph.startDestinationId, true)
//    graph.setStartDestination(newHomeRoute)
//    navigate(newHomeRoute)
    graph.setStartDestination(newHomeRoute)
    navigate(newHomeRoute) {
        popUpTo(0)
        launchSingleTop = true
    }
}