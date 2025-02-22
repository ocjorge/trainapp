package com.example.trainapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlertDao {
    @Query("SELECT * FROM alert ORDER BY timestamp DESC")
    fun getAllAlerts(): List<Alert>

    @Insert
    fun insertAlert(alert: Alert)
}

