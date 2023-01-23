package com.domain.android.demo.data.remote.dto

import com.google.gson.annotations.SerializedName


data class PhotosDto(
    @SerializedName("page") val page: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("perpage") val perpage: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("photo") val photo: List<PhotoDto>

)
