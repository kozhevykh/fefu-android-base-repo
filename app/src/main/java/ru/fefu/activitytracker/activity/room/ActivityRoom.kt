package ru.fefu.activitytracker.activity.room


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.fefu.activitytracker.activity.cards.ListItem
import ru.fefu.activitytracker.activity.room.calc.*
import ru.fefu.activitytracker.map.cards.TypeCardName
import java.time.Duration
import java.time.LocalDateTime

@Entity (tableName = "my_database")
data class ActivityRoom(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val type: TypeCardName,
    @ColumnInfo(name = "start_time") val start: LocalDateTime,
    @ColumnInfo(name = "finish") val finish: LocalDateTime?,
    val coords: List<Pair<Double, Double>>
) {
    fun toMyCard(): ListItem.MyCard {
        return ListItem.MyCard(
            id,
            type.type,
            coords.getDistance().toFormattedDistance(),
            Duration.between(start, finish).toFormattedDurationBetween(),
            finish!!.toFinishDateOrTime(),
            start.toTime(),
            finish.toTime(),
        )
    }
}

data class DistanceUpdate(
    val id: Int,
    val coords: List<Pair<Double, Double>>,
)

data class FinishTimeUpdate(
    val id: Int,
    @ColumnInfo(name = "finish") val finish: LocalDateTime?,
)