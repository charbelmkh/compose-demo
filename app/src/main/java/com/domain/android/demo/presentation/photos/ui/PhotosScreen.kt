package com.domain.android.demo.presentation.photos.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.domain.android.demo.R
import com.domain.android.demo.presentation.photos.model.Photo
import com.domain.android.demo.presentation.photos.viewmodel.LoadingState
import com.domain.android.demo.presentation.photos.viewmodel.PhotosViewModel
import com.domain.android.demo.presentation.widgets.Loading
import com.domain.android.demo.presentation.widgets.MessageScreen
import com.domain.android.demo.presentation.widgets.SearchBar
import kotlinx.coroutines.flow.Flow


@Composable
fun PhotosScreen(viewModel: PhotosViewModel, onBack: () -> Unit) {
    BackHandler(onBack = onBack)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Column {
        SearchBar(
            placeHolderText = stringResource(id = R.string.searchbar_placeholder),
            onSearchTextChanged = viewModel::search
        )
        if (uiState.loadingState == LoadingState.Idle) {
            MessageScreen(text = stringResource(id = R.string.search_instruction))
        }
        uiState.photos?.let {
            PhotoList(photos = it)
        } ?: kotlin.run {
            Loading()
        }
    }
}

@Composable
fun PhotoList(photos: Flow<PagingData<Photo>>) {
    val lazyItems = photos.collectAsLazyPagingItems()

    LazyColumn {

        items(lazyItems.itemCount) { index ->
            PhotoItemCard(modifier = Modifier.fillMaxWidth(), photo = lazyItems[index]!!)
        }

        lazyItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(message = "error",
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() })
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = lazyItems.loadState.append as LoadState.Error
                    item {
                        ErrorItem(message = e.error.localizedMessage!!, onClickRetry = { retry() })
                    }
                }
            }
        }
    }
}
