package com.hfad.mvvmapply

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider

class TrackActivity : ComponentActivity() {

    private lateinit var viewModel: TrackViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, TrackViewModel.getViewModelFactory("123"))[TrackViewModel::class.java]

    }

}