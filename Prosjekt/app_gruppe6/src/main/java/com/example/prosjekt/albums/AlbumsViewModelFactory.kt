package com.example.prosjekt.albums

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.prosjekt.users.User


class AlbumsViewModelFactory(
    private val userProperty: User,
    private val application: Application) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumsViewModel::class.java)) {
            return AlbumsViewModel(userProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}