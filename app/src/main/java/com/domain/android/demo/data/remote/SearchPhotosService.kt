package com.domain.android.demo.data.remote

import com.domain.android.demo.data.remote.dto.SearchResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface SearchPhotosService {
    @GET("services/rest/?method=flickr.photos.search")
    suspend fun search(
        @Query("text") text: String, @Query("page") page: Int, @Query("per_page") per_page: Int = 25
    ): Response<SearchResponseDto>
}
