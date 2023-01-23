package com.domain.android.demo.presentation.photos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.domain.android.demo.data.Repository
import com.domain.android.demo.data.paged.MovieSource
import com.domain.android.demo.presentation.photos.model.Photo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class PhotosViewModel(
    private val dispatcher: CoroutineDispatcher, private val repo: Repository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PhotosScreenState(loadingState = LoadingState.Idle))
    val uiState: StateFlow<PhotosScreenState> = _uiState.asStateFlow()
    private var job: Job? = null

    fun search(text: String) {
        resetSearch()
        if (text.isNotBlank()) {
            job = viewModelScope.launch(dispatcher) {
                val pager: Flow<PagingData<Photo>> = Pager(PagingConfig(pageSize = itemPerPage)) {
                    MovieSource(repo, text)
                }.flow
                _uiState.value =
                    PhotosScreenState(photos = pager, loadingState = LoadingState.InitialLoading)
            }
        }
    }

    private fun resetSearch() {
        job?.cancel()
        _uiState.value = PhotosScreenState(loadingState = LoadingState.Idle)
    }

    companion object {
        const val itemPerPage = 25
    }

}
