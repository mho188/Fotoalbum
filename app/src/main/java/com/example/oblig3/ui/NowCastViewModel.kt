package com.example.oblig3.ui

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.oblig3.NowApplication
import com.example.oblig3.network.UserProperty
import kotlinx.coroutines.launch

enum class NowCastApiStatus { LOADING, ERROR, DONE }

class NowCastViewModel(val application: NowApplication): AndroidViewModel(application) {

    private val _status = MutableLiveData<NowCastApiStatus>()
    val status: LiveData<NowCastApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<UserProperty>>()
    val properties: LiveData<List<UserProperty>>
        get() = _properties

    init {
        getNowCast()
    }

    private fun getNowCast() {
        viewModelScope.launch {
            _status.value = NowCastApiStatus.LOADING
            try {
                _properties.value = application.retrofitService.getNowCast()
                _status.value = NowCastApiStatus.DONE
            } catch (e: Exception) {
                _status.value = NowCastApiStatus.ERROR
                val message = e.localizedMessage
                Log.d("WFA_LOG", message)
            }
        }
    }
}