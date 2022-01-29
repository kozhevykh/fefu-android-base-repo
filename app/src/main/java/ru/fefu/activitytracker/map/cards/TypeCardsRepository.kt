package ru.fefu.activitytracker.map.cards

class TypeCardsRepository {
    private val cardslist = listOf(
        TypeCard(
            "Велосипед",
            false
        ),
        TypeCard(
            "Бег",
            false
        ),
        TypeCard(
            "Почилить",
            false
        )
    )

    fun getTypeCards(): List<Any> = cardslist
} 