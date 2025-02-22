package com.example.trainapp.util

import org.junit.Assert.*
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class TicketValidatorTest {

    private val ticketValidator = TicketValidator()

    @Test
    fun testActiveTicket() {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val currentDate = sdf.format(Date())
        assertTrue(ticketValidator.isTicketActive(currentDate))
    }

    @Test
    fun testExpiredTicket() {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -2)
        val oldDate = sdf.format(calendar.time)
        assertFalse(ticketValidator.isTicketActive(oldDate))
    }
}

