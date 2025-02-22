package com.example.trainapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TravelHistory(
    @PrimaryKey val id: Int,
    val userId: Int,
    val from: String,
    val to: String,
    val date: String,
    val extraEquipment: Boolean
)

