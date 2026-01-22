package com.example.lokalmusicplayer.data.repository

import com.example.lokalmusicplayer.data.model.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MusicRepository @Inject constructor() {

    private val client = OkHttpClient()

    suspend fun searchSongs(query: String): List<Song> =
        withContext(Dispatchers.IO) {

            val url =
                "https://deezerdevs-deezer.p.rapidapi.com/search?q=$query"

            val request = Request.Builder()
                .url(url)
                .addHeader(
                    "X-RapidAPI-Key",
                    "PUT_YOUR_RAPID_API_KEY_HERE"
                )
                .addHeader(
                    "X-RapidAPI-Host",
                    "deezerdevs-deezer.p.rapidapi.com"
                )
                .build()

            val response = client.newCall(request).execute()
            val body = response.body?.string().orEmpty()

            mapJsonToSongs(body)
        }
}
