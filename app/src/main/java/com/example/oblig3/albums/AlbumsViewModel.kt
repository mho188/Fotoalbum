package com.example.album.albums

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.album.network.JsonApi
import com.example.album.users.UserProperty
import kotlinx.coroutines.launch

enum class JsonAlbumsApiStatus { LOADING, ERROR, DONE }

class AlbumsViewModel(userProperty: UserProperty,
                      app: Application
) : ViewModel() {

    private val _status = MutableLiveData<JsonAlbumsApiStatus>()

    val status: LiveData<JsonAlbumsApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<AlbumProperty>>()

    val properties: LiveData<List<AlbumProperty>>
        get() = _properties

    private val _navigateToSelectedAlbum = MutableLiveData<AlbumProperty>()
    val navigateToSelectedAlbum: LiveData<AlbumProperty>
        get() = _navigateToSelectedAlbum

    init {
        getAlbumProperties(userProperty.id.toString())
    }

    private fun getAlbumProperties(AlbumId: String) {
        viewModelScope.launch {
            _status.value = JsonAlbumsApiStatus.LOADING
            try {
                _properties.value = JsonApi.retrofitService.getAlbumProperties(AlbumId)
                _status.value = JsonAlbumsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = JsonAlbumsApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }

    }

    fun displayAlbumDetails(albumProperty: AlbumProperty) {
        _navigateToSelectedAlbum.value = albumProperty
    }

    fun displayAlbumDetailsComplete() {
        _navigateToSelectedAlbum.value = null
    }


}