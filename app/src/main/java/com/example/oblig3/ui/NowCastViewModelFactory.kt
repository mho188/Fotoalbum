package com.example.oblig3.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.oblig3.NowApplication

class NowCastViewModelFactory(private val application: NowApplication)
    : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NowCastViewModel::class.java)) {
            return NowCastViewModel(application) as T
        }
        throw IllegalArgumentException("Ukjent ViewModel-klasse.")
    }
}