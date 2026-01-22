package com.example.lokalmusicplayer.data.mapper

import com.example.lokalmusicplayer.data.model.Song
import com.example.lokalmusicplayer.data.remote.dto.SearchResponse
import com.example.lokalmusicplayer.data.remote.dto.SongDto

/* ---------------------------- */
/* SongDto → Song */
/* ---------------------------- */

fun SongDto.toSong(): Song {
    return Song(
        id = id.toString(),
        title = title,
        artist = artist.name,
        image = album.coverMedium,
        previewUrl = preview
    )
}

/* ---------------------------- */
/* SearchResponse → List<Song> */
/* ---------------------------- */

fun SearchResponse.toSongList(): List<Song> {
    return data.map { it.toSong() }
}
