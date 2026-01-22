package com.example.lokalmusicplayer.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("data")
    val data: List<SongDto>
)
