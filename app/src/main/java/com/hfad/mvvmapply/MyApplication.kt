package com.hfad.mvvmapply

import android.app.Application

//В примере не было реализации TracksRepositoryImpl, здесь он подсвечивается красным
class MyApplication: Application() {
    fun getRepository(): TracksRepositoryImpl {
        TracksRepositoryImpl(NetworkClientImpl())
    }

    fun provideTracksInteractor(): TrackInteractor {
        return TracksInteractorImpl(getRepository())
    }
}