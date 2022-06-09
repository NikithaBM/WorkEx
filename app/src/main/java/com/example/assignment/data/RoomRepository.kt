package com.example.assignment.data

class RoomRepository(private var dao: WorkExDao) {

    suspend fun saveEmail(credential: Credentials)
    {
        dao.saveEmail(credential)
    }
}