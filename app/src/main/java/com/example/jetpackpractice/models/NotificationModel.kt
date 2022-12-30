package com.example.jetpackpractice.models

data class NotificationModel(var name: String)


object NotificationData {
    // Sample conversation data
    val conversationSample = listOf(
        NotificationModel(
            "Bitcoin and Ethereum Plummet 8%, Erasing Yesterday's Bullish Gains"
        ),
        NotificationModel(
            "Bitcoin Price Retreats Below \$19,000 – Will It Slide Under \$18,000 This Week?"
        ),
        NotificationModel(
            "Bitcoin Price Will Soar 13% By October 31, Crypto Community Predicts"
        )
    )
}