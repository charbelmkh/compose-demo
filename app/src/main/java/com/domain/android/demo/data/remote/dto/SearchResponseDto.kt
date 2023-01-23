package com.domain.android.demo.data.remote.dto

import com.google.gson.annotations.SerializedName


data class SearchResponseDto(
    @SerializedName("photos") val photos: PhotosDto,
    @SerializedName("stat") val stat: String
)
