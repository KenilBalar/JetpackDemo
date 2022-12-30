package com.example.jetpackpractice.models

import com.example.jetpackpractice.R

data class WatchlistModel(
    var name: String,
    var currencyValue: String,
    var growth: String,
    var icon : Int
)


object WatchlistData {
    // Sample conversation data
    val watchList = listOf(
        WatchlistModel(
            "Bitcoin",
            "16,46,589",
            "-7.22",
            R.drawable.ic_btc
        ),
        WatchlistModel(
            "Ethereum",
            "1,12,500",
            "-7.38",
            R.drawable.ic_eth
        ),
        WatchlistModel(
            "Dogecoin",
            "5.2296",
            "-5.44",
            R.drawable.ic_doge
        ),
        WatchlistModel(
            "Bitcoin Cash",
            "9762.08",
            "+2.95",
            R.drawable.ic_bch
        ),
        WatchlistModel(
            "USD Coin",
            "86.0",
            "+0.03",
            R.drawable.ic_usdc
        )





    )
}