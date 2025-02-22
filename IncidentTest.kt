package com.example.trainapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.trainapp.data.AppDatabase
import com.example.trainapp.data.Incident
import com.example.trainapp.data.IncidentDao
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class IncidentTest {
    private lateinit var incidentDao: IncidentDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        incidentDao = db.incidentDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndReadIncident() {
        val incident = Incident(userId = 1, description = "Test incident", location = "Test location", timestamp = System.currentTimeMillis())
        incidentDao.insertIncident(incident)
        val incidents = incidentDao.getAllIncidents()
        assert(incidents.isNotEmpty())
        assert(incidents[0].description == "Test incident")
    }
}

