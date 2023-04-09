package com.hfad.mvvmapply

import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.hfad.mvvmapply.MyApplication
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TrackViewModel(
    private val trackId: Int,
    private val tracksInteractor: TracksInteractor,
) : ViewModel() {

    private var loadingLiveData = MutableLiveData(true)

    // 1
    init {
        tracksInteractor.loadSomeData(
            onComplete = {
                // 2
                loadingLiveData.postValue(false)
                // или
                // 3
                loadingLiveData.value = false
            }
        )
    }

    fun getLoadingLiveData(): LiveData<Boolean> = loadingLiveData

    companion object {
        fun getViewModelFactory(trackId: Int): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val interactor = (this[APPLICATION_KEY] as MyApplication).provideTracksInteractor()

                TrackViewModel(
                    trackId,
                    interactor,
                )
            }
        }
    }
}