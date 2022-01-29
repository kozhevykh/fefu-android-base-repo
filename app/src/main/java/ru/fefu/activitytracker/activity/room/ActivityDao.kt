package ru.fefu.activitytracker.activity.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ActivityDao {
    @Query("SELECT * FROM my_database ORDER BY finish DESC")
    fun getAll(): LiveData<List<ActivityRoom>>

    @Query("SELECT * FROM my_database WHERE id=:id")
    fun getById(id: Int): ActivityRoom

    @Insert
    fun insert(activity : ActivityRoom)

    @Delete
    fun delete(activity: ActivityRoom)
}