package com.example.trainapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE id = :id")
    fun getUser(id: Int): User

    @Insert
    fun insertUser(user: User)
}

