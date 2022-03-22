package com.example.prosjekt.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prosjekt.network.JsonApi
import kotlinx.coroutines.launch

enum class UserApiStatus { LOADING, ERROR, DONE }

class UsersViewModel() : ViewModel() {

    private val _status = MutableLiveData<UserApiStatus>()

    val status: LiveData<UserApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<User>>()

    val properties: LiveData<List<User>>
        get() = _properties

    private val _navigateToSpecificUser = MutableLiveData<User?>()
    val navigateToSelectedUser: MutableLiveData<User?>
        get() = _navigateToSpecificUser

    init {
        getAllUsers()
    }

    private fun getAllUsers() {
        viewModelScope.launch {
            _status.value = UserApiStatus.LOADING
            try {
                _properties.value = JsonApi.retrofitService.getAllUserProperties()
                _status.value = UserApiStatus.DONE
            } catch (e: Exception) {
                _status.value = UserApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }

    }

    fun displayUserDetails(userProperty: User) {
        _navigateToSpecificUser.value = userProperty
    }

    fun displayUserDetailsComplete() {
        _navigateToSpecificUser.value = null
    }
}