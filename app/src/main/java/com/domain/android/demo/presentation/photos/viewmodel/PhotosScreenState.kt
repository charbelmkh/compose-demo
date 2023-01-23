package com.domain.android.demo.presentation.photos.viewmodel

import androidx.paging.PagingData
import com.domain.android.demo.presentation.photos.model.Photo
import kotlinx.coroutines.flow.Flow

class PhotosScreenState(val photos: Flow<PagingData<Photo>>? = null, val loadingState: LoadingState) {

}

sealed class LoadingState{
    object InitialLoading:LoadingState()
    object Idle:LoadingState()
}



