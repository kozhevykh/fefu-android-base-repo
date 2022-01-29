package ru.fefu.activitytracker.activity.cards

data class UserCard (
    val distance : String,
    val username : String,
    val duration : String,
    val type : String,
    val start_time : String
) : ICard