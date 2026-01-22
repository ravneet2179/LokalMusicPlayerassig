package com.example.lokalmusicplayer.ui.player

import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import com.example.lokalmusicplayer.data.model.Song
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor() : ViewModel() {

    private var mediaPlayer: MediaPlayer? = null
    private var queue: List<Song> = emptyList()
    private var index = 0

    private val _current = MutableStateFlow<Song?>(null)
    val current = _current.asStateFlow()

    private val _playing = MutableStateFlow(false)
    val playing = _playing.asStateFlow()

    fun setQueue(list: List<Song>, start: Song) {
        queue = list
        index = list.indexOf(start)
        play(start)
    }

    private fun play(song: Song) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer().apply {
            setDataSource(song.previewUrl)
            prepare()
            start()
        }
        _current.value = song
        _playing.value = true
    }

    fun pause() {
        mediaPlayer?.pause()
        _playing.value = false
    }

    fun resume() {
        mediaPlayer?.start()
        _playing.value = true
    }

    fun next() {
        if (queue.isEmpty()) return
        index = (index + 1) % queue.size
        play(queue[index])
    }

    fun prev() {
        if (queue.isEmpty()) return
        index = if (index - 1 < 0) queue.lastIndex else index - 1
        play(queue[index])
    }

    override fun onCleared() {
        mediaPlayer?.release()
        super.onCleared()
    }
}
