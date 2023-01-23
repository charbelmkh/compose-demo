package com.domain.android.demo.presentation.photos.model

import com.domain.android.demo.data.remote.dto.PhotoDto


data class Photo(val id :String,val url:String,val title:String) {

    companion object{
        fun fromPhotoDto(dto:PhotoDto):Photo{
            return Photo(dto.id,"https://farm${dto.farm}.staticflickr.com/${dto.server}/${dto.id}_${dto.secret}.jpg",dto.title)
        }
    }
}
