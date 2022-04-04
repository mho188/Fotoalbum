package com.example.prosjekt.albums

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prosjekt.network.JsonApi
import com.example.prosjekt.users.User
import kotlinx.coroutines.launch

enum class JsonAlbumsApiStatus { LOADING, ERROR, DONE }

class AlbumsViewModel(userProperty: User,
                      app: Application
) : ViewModel() {

    private val _status = MutableLiveData<JsonAlbumsApiStatus>()

    val status: LiveData<JsonAlbumsApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<Album>>()

    val properties: LiveData<List<Album>>
        get() = _properties

    private val _navigateToSelectedAlbum = MutableLiveData<Album?>()
    val navigateToSelectedAlbum: MutableLiveData<Album?>
        get() = _navigateToSelectedAlbum

    init {
        getAlbum(userProperty.id.toString())
    }

    private fun getAlbum(AlbumId: String) {
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

    fun displayAlbumDetails(albumProperty: Album) {
        _navigateToSelectedAlbum.value = albumProperty
    }

    fun displayAlbumDetailsComplete() {
        _navigateToSelectedAlbum.value = null
    }


}