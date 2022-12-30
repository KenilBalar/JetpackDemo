package com.example.jetpackpractice.models

import com.example.jetpackpractice.R

data class CurrencyModel(
    var name: String,
    var currencyValue: String,
    var growth: String,
    var icon : Int
)


object CurrencyData {
    // Sample conversation data
    val currencyList = listOf(
        CurrencyModel(
            "Bitcoin",
            "16,46,589",
            "-7.22",
            R.drawable.ic_btc
        ),
        CurrencyModel(
            "Ethereum",
            "1,12,500",
            "-7.38",
            R.drawable.ic_eth
        ),
        CurrencyModel(
            "Tether",
            "86.38",
            "+0.03",
            R.drawable.ic_usdt
        ),
        CurrencyModel(
            "Dogecoin",
            "5.2296",
            "-5.44",
            R.drawable.ic_doge
        ),
        CurrencyModel(
            "Golem",
            "24.1063",
            "+3.95",
            R.drawable.ic_gnt
        ),
        CurrencyModel(
            "Avalanche",
            "1514.81",
            "+1.15",
            R.drawable.ic_avax
        ),
        CurrencyModel(
            "Bitcoin Cash",
            "9762.08",
            "+2.95",
            R.drawable.ic_bch
        ),
        CurrencyModel(
            "Dai",
            "85.85",
            "-0.03",
            R.drawable.ic_dai
        ),CurrencyModel(
            "Filecoin",
            "477.01",
            "+1.56",
            R.drawable.ic_fil
        ),CurrencyModel(
            "The Sandbox",
            "72.44",
            "+2.72",
            R.drawable.ic_sand
        ),CurrencyModel(
            "TRON",
            "5.16",
            "+2.23",
            R.drawable.ic_trx
        ),CurrencyModel(
            "USD Coin",
            "86.0",
            "+0.03",
            R.drawable.ic_usdc
        ),
        CurrencyModel(
            "XRP",
            "37.98",
            "+2.38",
            R.drawable.ic_xrp
        )





    )
}