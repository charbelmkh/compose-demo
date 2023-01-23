package com.domain.android.demo.data

import com.domain.android.demo.data.remote.dto.SearchResponseDto
import retrofit2.Response


interface Repository {
    suspend fun search(text: String, page: Int): Response<SearchResponseDto>
}
