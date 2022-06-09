package com.example.assignment.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.assignment.Utils.Utility.hideProgressBar
import com.example.assignment.Utils.Utility.showProgressBar

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserRepository {

    fun getMutableLiveData(context: Context) : MutableLiveData<ArrayList<UserProfile>>{

        val mutableLiveData = MutableLiveData<ArrayList<UserProfile>>()

        context.showProgressBar()

        ApiClient.apiService.getUsers().enqueue(object : Callback<MutableList<UserProfile>> {
            override fun onFailure(call: Call<MutableList<UserProfile>>, t: Throwable) {
                hideProgressBar()
                Log.e("error", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<MutableList<UserProfile>>,
                response: Response<MutableList<UserProfile>>
            ) {
                hideProgressBar()
                val usersResponse = response.body()
                usersResponse?.let { mutableLiveData.value = it as ArrayList<UserProfile> }
            }

        })

        return mutableLiveData
    }

    suspend fun insertEmail(email: String)
    {

    }

}