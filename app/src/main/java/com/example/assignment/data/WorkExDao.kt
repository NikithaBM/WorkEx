package com.example.assignment.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.assignment.data.Credentials

@Dao
interface WorkExDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun saveEmail(credentials: Credentials)


}