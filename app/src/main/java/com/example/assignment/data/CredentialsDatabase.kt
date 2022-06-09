package com.example.assignment.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Credentials::class],
    version = 1,
    exportSchema = true
)

abstract class CredentialsDatabase: RoomDatabase() {
    abstract val dao: WorkExDao

    companion object{

        @Volatile
        private var INSTANCE : CredentialsDatabase? = null

        fun getInstance(context: Context): CredentialsDatabase {
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    CredentialsDatabase::class.java,
                    "WorkExCred")
                    .build()
            }
        }
    }

}