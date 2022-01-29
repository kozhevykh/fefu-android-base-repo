package ru.fefu.activitytracker.activity.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.fefu.activitytracker.activity.room.calc.Converters

@Database(entities = [ActivityRoom::class], version = 2)
@TypeConverters(Converters::class)
abstract class MyDatabase: RoomDatabase() {
    abstract fun activityDao(): ActivityDao
}