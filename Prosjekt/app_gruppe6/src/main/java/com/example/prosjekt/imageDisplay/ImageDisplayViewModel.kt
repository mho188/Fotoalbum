package com.example.prosjekt.imageDisplay

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prosjekt.images.Image
import com.example.prosjekt.network.JsonApi
import kotlinx.coroutines.launch

enum class JsonImageDisplayApiStatus { LOADING, ERROR, DONE }

class ImageDisplayViewModel(
    imageProperty: Image,
    app: Application
) : ViewModel() {

    private val _status = MutableLiveData<JsonImageDisplayApiStatus>()

    val status: LiveData<JsonImageDisplayApiStatus>
        get() = _status

    private val _selectedImage = MutableLiveData<Image>()

    val selectedImage: LiveData<Image>
        get() = _selectedImage

    init {
        _selectedImage.value = imageProperty
    }

    fun patchEdits(newTitle: String) {
        viewModelScope.launch {
            try {
                _selectedImage.value?.title = newTitle
                _selectedImage.value = JsonApi.retrofitService.editTitle(
                    _selectedImage.value?.id.toString(),
                    _selectedImage.value
                )
                _status.value = JsonImageDisplayApiStatus.DONE
            } catch (e: Exception) {
                _status.value = JsonImageDisplayApiStatus.ERROR
            }
        }
    }

    fun deleteImage() {
        viewModelScope.launch {
            _status.value = JsonImageDisplayApiStatus.LOADING
            try {
                _selectedImage.value =
                    JsonApi.retrofitService.deletePhoto(_selectedImage.value?.id.toString())
                _status.value = JsonImageDisplayApiStatus.DONE
            } catch (e: Exception) {
                _status.value = JsonImageDisplayApiStatus.ERROR
            }
        }
    }


}

