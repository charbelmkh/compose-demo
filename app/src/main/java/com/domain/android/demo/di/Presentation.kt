package com.domain.android.demo.di

import com.domain.android.demo.presentation.photos.viewmodel.PhotosViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModels = module {
    viewModel {
        PhotosViewModel(get(),get())
    }
}

