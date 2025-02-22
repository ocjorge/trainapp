package com.example.trainapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TicketDao {
    @Query("SELECT * FROM ticket WHERE userId = :userId")
    fun getTicketsForUser(userId: Int): List<Ticket>

    @Insert
    fun insertTicket(ticket: Ticket)
}

