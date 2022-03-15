package com.example.oblig3.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oblig3.network.AlbumUserApi
import com.example.oblig3.network.UserProperty
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class OverviewViewModel : ViewModel(){
    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    private val _users = MutableLiveData<List<UserProperty>>()

    val users: LiveData<List<UserProperty>>
        get() = _users

    init {
        getUserProperties()
    }


    private fun getUserProperties() {
        viewModelScope.launch {
            try {
                var listResult = AlbumUserApi.retrofitServiceUsers.getUsers()
                _response.value = "Antall users hentet ut fra nettsiden: ${listResult.size} "
                if (listResult.size > 0) {
                    _users.value = listResult
                }
            }
            catch (e: Exception) {
                _response.value = "Failure" + e.message
            }
        }

    }
}