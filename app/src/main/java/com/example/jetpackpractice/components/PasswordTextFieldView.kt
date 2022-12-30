package com.example.jetpackpractice.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.jetpackpractice.R

@Composable
fun PasswordTextFieldView(lableName : String, valueName: MutableState<TextFieldValue> = remember { mutableStateOf(
    TextFieldValue()
) } ){

    TextField(
        label = { Text(text = lableName) },
        value = valueName.value,
        singleLine = true,
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, tint = colorResource(id = R.color.dark_blue), contentDescription = "passwordIcon") },
        modifier = Modifier
            .border(
                width = (0.5).dp, color = colorResource(
                    id = R.color.dark_blue
                ), shape = RoundedCornerShape(10.dp)
            )
            .fillMaxWidth(),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password , imeAction = ImeAction.Done),
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