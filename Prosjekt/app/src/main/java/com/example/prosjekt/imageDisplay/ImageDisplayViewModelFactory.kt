package com.example.prosjekt.imageDisplay

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.prosjekt.images.Image

class ImageDisplayViewModelFactory(
    private val imageProperty: Image,
    private val application: Application) : ViewModelProvider.Factory {

        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ImageDisplayViewModel::class.java)) {
                return ImageDisplayViewModel(imageProperty, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}