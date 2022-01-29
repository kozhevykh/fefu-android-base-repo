package ru.fefu.activitytracker.activity.cards

import ru.fefu.activitytracker.activity.cards.DateCard
import ru.fefu.activitytracker.activity.cards.ICard
import ru.fefu.activitytracker.activity.cards.MyCard
import ru.fefu.activitytracker.activity.cards.UserCard

class UserCardsRepository {
    private val cardslist = listOf(
        DateCard(
            "Вчера"
        ),
        UserCard(
            "11.32 км",
            "@van_dorne",
            "2 часа 16 минут",
            "Сёрфинг",
            "13 часов назад"
        ),
        UserCard(
            "228 м",
            "@pressf",
            "14 часов 48 минут",
            "Качели",
            "14 часов назад"
        ),
        UserCard(
            "10 км",
            "@morgana_farn",
            "1 час 10 минут",
            "Бег",
            "14 часов назад"
        )
    )

    fun getUsersCards(): List<Any> = cardslist
} 