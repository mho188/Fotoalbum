package com.example.album.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.album.network.JsonApi
import kotlinx.coroutines.launch

enum class JsonUsersApiStatus { LOADING, ERROR, DONE }

class UsersViewModel() : ViewModel() {

    private val _status = MutableLiveData<JsonUsersApiStatus>()

    val status: LiveData<JsonUsersApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<UserProperty>>()

    val properties: LiveData<List<UserProperty>>
        get() = _properties

    private val _navigateToSelectedUser = MutableLiveData<UserProperty>()
    val navigateToSelectedUser: LiveData<UserProperty>
        get() = _navigateToSelectedUser

    init {
        getUserProperties()
    }

    private fun getUserProperties() {
        viewModelScope.launch {
            _status.value = JsonUsersApiStatus.LOADING
            try {
                _properties.value = JsonApi.retrofitService.getUserProperties()
                _status.value = JsonUsersApiStatus.DONE
            } catch (e: Exception) {
                _status.value = JsonUsersApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }

    }

    fun displayUserDetails(userProperty: UserProperty) {
        _navigateToSelectedUser.value = userProperty
    }

    fun displayUserDetailsComplete() {
        _navigateToSelectedUser.value = null
    }
}