package com.example.trainapp.util

import java.text.SimpleDateFormat
import java.util.*

class TicketValidator {
    fun isTicketActive(ticketDate: String): Boolean {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val ticketDateTime = sdf.parse(ticketDate)
        val currentDateTime = Calendar.getInstance().time
        
        // Assuming a ticket is valid for 24 hours
        return (currentDateTime.time - ticketDateTime.time) < 24 * 60 * 60 * 1000
    }
}

