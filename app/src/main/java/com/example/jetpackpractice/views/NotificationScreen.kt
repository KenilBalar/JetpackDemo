package com.example.jetpackpractice.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackpractice.models.NotificationData
import com.example.jetpackpractice.models.NotificationModel
import com.example.jetpackpractice.R
import com.example.jetpackpractice.ui.theme.JetpackPracticeTheme

@Preview(showBackground = true)
@Composable
fun NotificationScreen() {
    JetpackPracticeTheme() {
        Conversations(NotificationData.conversationSample)
    }
}

@Composable
fun Conversations(NotificationModel: List<NotificationModel>) {
    Column(modifier = Modifier.padding(10.dp)) {
        Row(modifier = Modifier.padding(bottom = 16.dp , top = 20.dp)) {
            Text(
                text = "Notifications",
                fontSize = 26.sp,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(
                    start = 2.dp,
                    end = 2.dp
                ).align(Alignment.CenterVertically),
            )
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = "Android",
                modifier = Modifier
                    .clip(CircleShape)
                    .height(25.dp)
                    .width(25.dp).align(Alignment.CenterVertically),
                colorFilter = ColorFilter.tint(color = colorResource(id = R.color.yellow))
            )
        }
        LazyColumn() {
            items(NotificationModel) { modelData -> Greeting(modelData) }
        }
    }

}

@Composable
fun Greeting(NotificationModel: NotificationModel) {
    // We keep track if the message is expanded or not in this
    // variable
    var isExpanded by remember { mutableStateOf(false) }
    Column() {

        Surface(
            shape = RoundedCornerShape(12.dp),
            elevation = 3.dp,
            color = colorResource(id = R.color.white),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
        ) {
            Row(modifier = Modifier.padding(all = 10.dp)) {

                Text(
                    text = NotificationModel.name,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.primary,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 2,
                    modifier = Modifier.padding(
                        start = 2.dp,
                        top = 2.dp,
                        bottom = 2.dp,
                        end = 2.dp
                    ),
                )
            }
        }
    }
}