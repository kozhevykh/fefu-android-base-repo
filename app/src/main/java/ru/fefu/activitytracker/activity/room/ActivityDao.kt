package ru.fefu.activitytracker.activity.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ActivityDao {
    @Query("SELECT * FROM my_database ORDER BY finish DESC")
    fun getAll(): LiveData<List<ActivityRoom>>

    @Query("SELECT COUNT (*) FROM my_database ORDER BY finish DESC")
    fun getSIZE(): Int

    @Query("SELECT * FROM my_database WHERE id=:id")
    fun getById(id: Int): ActivityRoom

    @Query("select * from my_database where id=:id")
    fun getLiveById(id: Int): LiveData<ActivityRoom>

    @Query("select * from my_database order by start_time desc limit 1")
    fun getLast(): ActivityRoom?

    @Query("delete from my_database where id=:id")
    fun deleteById(id: Int)

    @Update(entity = ActivityRoom::class)
    fun updatePath(updateObj: DistanceUpdate)

    @Update(entity = ActivityRoom::class)
    fun updateFinishTime(updateObj: FinishTimeUpdate)

    @Insert
    fun insert(activity : ActivityRoom) : Long

    @Delete
    fun delete(activity: ActivityRoom)
}