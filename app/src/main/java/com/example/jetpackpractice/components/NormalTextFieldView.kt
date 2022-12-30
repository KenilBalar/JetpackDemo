package com.example.jetpackpractice.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import com.example.jetpackpractice.R
import com.example.jetpackpractice.navigation.Constants.EMAIL
import com.example.jetpackpractice.navigation.Constants.PASSWORD
import com.example.jetpackpractice.navigation.Constants.USER

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NormalTextFieldView(
    lableName: String,
    valueName: MutableState<String> = remember {
        mutableStateOf("")
    },
    type: String,
    modifier: Modifier = Modifier
        .border(
            width = (0.5).dp, color = colorResource(
                id = R.color.dark_blue
            ), shape = RoundedCornerShape(10.dp)
        )
        .fillMaxWidth()
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        label = { Text(text = lableName) },
        value = valueName.value,
        singleLine = true,
        leadingIcon = { Icon(
            imageVector = when(type){
                EMAIL->{ Icons.Default.Email}
                USER->{Icons.Default.Person}
                PASSWORD->{Icons.Default.Lock}
                else-> Icons.Default.Person } ,
            tint = colorResource(id = R.color.dark_blue),
            contentDescription = "Icon"
        )},
        modifier = modifier,
        visualTransformation = when(type){
            PASSWORD->{PasswordVisualTransformation()}
            else->VisualTransformation.Companion.None
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = when(type){
                EMAIL->{KeyboardType.Email}
                USER->{KeyboardType.Text}
                PASSWORD->{KeyboardType.Password}
                else->KeyboardType.Text
            },
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {keyboardController?.hide()}
        ),
        onValueChange = { valueName.value = it },
        colors = TextFieldDefaults.textFieldColors(
            textColor = colorResource(
                id = R.color.dark_blue
            ),
            backgroundColor = colorResource(
                id = R.color.white
            ),
            unfocusedIndicatorColor = colorResource(
                id = R.color.white
            ),
            focusedIndicatorColor = colorResource(
                id = R.color.white
            ),
            unfocusedLabelColor = colorResource(
                id = R.color.dark_blue
            ).copy(0.5f),
            focusedLabelColor = colorResource(
                id = R.color.dark_blue
            )

        )
    )
}