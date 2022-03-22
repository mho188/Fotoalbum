package com.example.prosjekt.images

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prosjekt.albums.Album
import com.example.prosjekt.network.JsonApi
import kotlinx.coroutines.launch

enum class JsonImagesApiStatus { LOADING, ERROR, DONE }

class ImagesViewModel (albumProperty: Album,
                       app: Application
) : ViewModel(){

    private val _status = MutableLiveData<JsonImagesApiStatus>()

    val status: LiveData<JsonImagesApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<Image>>()

    val properties: LiveData<List<Image>>
        get() = _properties

    private val _navigateToSelectedImage = MutableLiveData<Image?>()
    val navigateToSelectedImage: MutableLiveData<Image?>
        get() = _navigateToSelectedImage

    init {
        getImage(albumProperty.id.toString())
    }

    private fun getImage(ImageId: String) {
        viewModelScope.launch {
            _status.value = JsonImagesApiStatus.LOADING
            try {
                _properties.value = JsonApi.retrofitService.getImageProperties(ImageId)
                _status.value = JsonImagesApiStatus.DONE
            } catch (e: Exception) {
                _status.value = JsonImagesApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    fun displayImageDetails(imageProperty: Image) {
        _navigateToSelectedImage.value = imageProperty
    }

    fun displayImageDetailsComplete() {
        _navigateToSelectedImage.value = null
    }
}