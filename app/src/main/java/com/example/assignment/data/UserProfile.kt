package com.example.assignment.data

import com.example.assignment.data.Address
import com.example.assignment.data.Company

data class UserProfile(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)