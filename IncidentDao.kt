package com.example.trainapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IncidentDao {
    @Query("SELECT * FROM incident ORDER BY timestamp DESC")
    fun getAllIncidents(): List<Incident>

    @Insert
    fun insertIncident(incident: Incident)
}

