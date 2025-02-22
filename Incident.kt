package com.example.trainapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Incident(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val description: String,
    val location: String,
    val timestamp: Long
)

