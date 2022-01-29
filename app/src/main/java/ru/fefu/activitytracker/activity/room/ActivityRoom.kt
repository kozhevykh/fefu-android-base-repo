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
    @ColumnInfo(name = "type") val type: Int,
    @ColumnInfo(name = "start_time") val start: LocalDateTime,
    @ColumnInfo(name = "finish") val finish: LocalDateTime,
    @ColumnInfo(name = "coords") val coords: List<Pair<Double, Double>>
) {
    fun toMyCard(): ListItem.MyCard {
        return ListItem.MyCard(
            id,
            TypeCardName.values()[type].type,
            coords.getDistance().toFormattedDistance(),
            Duration.between(start, finish).toFormattedDurationBetween(),
            finish.toFinishDateOrTime(),
            start.toTime(),
            finish.toTime(),
        )
    }
}