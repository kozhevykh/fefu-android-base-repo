package ru.fefu.activitytracker.activity.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.fefu.activitytracker.activity.room.calc.Converters

@TypeConverters(Converters::class)
@Database(
    version = 3 ,
    entities = [ActivityRoom::class],
)
abstract class MyDatabase: RoomDatabase() {
    abstract fun activityDao(): ActivityDao
}