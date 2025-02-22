package com.example.trainapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TravelHistoryDao {
    @Query("SELECT * FROM travelhistory WHERE userId = :userId")
    fun getTravelHistoryForUser(userId: Int): List<TravelHistory>

    @Insert
    fun insertTravelHistory(travelHistory: TravelHistory)
}

