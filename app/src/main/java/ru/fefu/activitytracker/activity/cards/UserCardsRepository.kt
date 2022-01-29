package ru.fefu.activitytracker.activity.cards

class UserCardsRepository {
    private val cardslist = listOf(
        ListItem.DateCard(
            "Вчера"
        ),
        ListItem.UserCard(
            "14.32 км",
            "@van_darkholme",
            "2 часа 46 минут",
            "Сёрфинг",
            "14 часов назад"
        ),
        ListItem.UserCard(
            "228 м",
            "@techniquepasha",
            "14 часов 48 минут",
            "Качели",
            "14 часов назад"
        ),
        ListItem.UserCard(
            "10 км",
            "@morgen_shtern",
            "1 час 10 минут",
            "Езда на кадилак",
            "14 часов назад"
        )
    )

    fun getUsersCards(): List<Any> = cardslist
}