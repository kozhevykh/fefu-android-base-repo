package ru.fefu.activitytracker.activity.cards

data class MyCard (
    val distance : String,
    val duration : String,
    val type : String,
    val start_time : String
) : ICard