package com.hfad.mvvmapply


object Creator {
    fun getRepository(): TracksRepositoryImpl {
        TracksRepositoryImpl(NetworkClientImpl())
    }

    fun provideTracksInteractor(): TrackInteractor {
        return TracksInteractorImpl(getRepository())
    }
}