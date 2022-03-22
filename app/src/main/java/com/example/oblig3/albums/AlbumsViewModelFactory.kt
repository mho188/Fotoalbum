package com.example.album.albums

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.album.users.UserProperty


class AlbumsViewModelFactory(
        private val userProperty: UserProperty,
        private val application: Application) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumsViewModel::class.java)) {
            return AlbumsViewModel(userProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}