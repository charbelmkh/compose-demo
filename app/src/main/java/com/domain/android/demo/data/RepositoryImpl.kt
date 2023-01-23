package com.domain.android.demo.data

import com.domain.android.demo.data.remote.SearchPhotosService
import com.domain.android.demo.data.remote.dto.SearchResponseDto
import retrofit2.Response


class RepositoryImpl(private val searchPhotosService: SearchPhotosService):Repository {

   override suspend fun search(text: String, page: Int): Response<SearchResponseDto> =
        searchPhotosService.search(text, page)

}
