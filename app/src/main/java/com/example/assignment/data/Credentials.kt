package com.example.assignment.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Credentials (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val email: String )