package com.example.assignment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.assignment.data.*
import com.example.assignment.Utils.Utility.isInternetAvailable
import kotlinx.coroutines.launch


class UserViewModel(application: Application) : AndroidViewModel(application) {

    private var listData = MutableLiveData<ArrayList<UserProfile>>()
    private var repository: RoomRepository

    init {
        val userRepository: UserRepository by lazy {
            UserRepository
        }

            val credDb = CredentialsDatabase.getInstance(application).dao
            repository = RoomRepository(credDb)


        if (application.isInternetAvailable()) {
            listData = userRepository.getMutableLiveData(application)
        }
    }

    fun getData(): MutableLiveData<ArrayList<UserProfile>> {
        return listData
    }

    fun saveEmail(credential: Credentials){
        viewModelScope.launch {
            repository.saveEmail(credential)
        }
    }
}

