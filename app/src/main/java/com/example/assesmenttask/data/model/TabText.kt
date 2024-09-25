package com.example.assesmenttask.data.model


val tobBarItems = listOf(
    TabText(
        index = 0,
        title = "Accounts"
    ),
    TabText(
        index = 1,
        title = "Cards"
    ),
)

data class TabText(
    val index : Int,
    val title: String,
)
