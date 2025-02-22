package com.example.trainapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Alert(
    @PrimaryKey val id: Int,
    val title: String,
    val message: String,
    val timestamp: Long
)

