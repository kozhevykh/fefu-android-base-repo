package ru.fefu.activitytracker.activity.cards

import ru.fefu.activitytracker.activity.cards.DateCard
import ru.fefu.activitytracker.activity.cards.ICard
import ru.fefu.activitytracker.activity.cards.MyCard

class PersonalCardsRepository {
    private val cardslist = listOf(
        DateCard(
            "Вчера"
        ),
        MyCard(
            "14.32 км",
            "2 часа 46 минут",
            "Сёрфинг",
            "14 часов назад"
        ),
        DateCard(
            "Май 2022 года"
        ),
        MyCard(
            "1000 м",
            "60 минут",
            "Велосипед",
            "29.05.2022"
        ),
    )

    fun getMyCards(): List<Any> = cardslist
} 