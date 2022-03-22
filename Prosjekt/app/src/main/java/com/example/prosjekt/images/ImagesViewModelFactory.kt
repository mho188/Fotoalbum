package com.example.prosjekt.images

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.prosjekt.albums.Album

class ImagesViewModelFactory(
    private val albumProperty: Album,
    private val application: Application) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImagesViewModel::class.java)) {
            return ImagesViewModel(albumProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}