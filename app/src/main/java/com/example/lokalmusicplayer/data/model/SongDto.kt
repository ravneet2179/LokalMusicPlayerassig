package com.example.lokalmusicplayer.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SongDto(
    @SerializedName("id")
    val id: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("preview")
    val preview: String,

    @SerializedName("artist")
    val artist: ArtistDto,

    @SerializedName("album")
    val album: AlbumDto
)

/* ---------- Nested DTOs ---------- */

data class ArtistDto(
    @SerializedName("name")
    val name: String
)

data class AlbumDto(
    @SerializedName("cover_medium")
    val coverMedium: String
)
