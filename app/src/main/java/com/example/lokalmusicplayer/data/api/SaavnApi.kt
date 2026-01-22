package com.example.lokalmusicplayer.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SaavnApi {

    @GET("search/songs")
    suspend fun searchSongs(
        @Query("query") query: String,
        @Query("limit") limit: Int = 20
    ): SearchResponse

    @GET("songs/{id}")
    suspend fun getSong(
        @Path("id") id: String
    ): SongDetailResponse
}
