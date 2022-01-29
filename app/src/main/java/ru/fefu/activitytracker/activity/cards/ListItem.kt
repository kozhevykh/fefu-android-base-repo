package ru.fefu.activitytracker.activity.cards

sealed class ListItem {
    class DateCard (
        val date: String
    ) : ListItem()

    class MyCard (
        val id: Int,
        val duration: String,
        val distance: String,
        val type: String,
        val start_time: String,
        val start_coords: String,
        val finish_coords: String,
    ) : ListItem()

    class UserCard (
        val distance: String,
        val username: String,
        val duration: String,
        val type: String,
        val start_time: String
    ) : ListItem()
}