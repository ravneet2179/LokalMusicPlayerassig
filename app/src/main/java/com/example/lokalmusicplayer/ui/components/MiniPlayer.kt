package com.example.lokalmusicplayer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lokalmusicplayer.ui.player.PlayerViewModel

@Composable
fun MiniPlayer(
    vm: PlayerViewModel,
    onOpen: () -> Unit
) {

    val song = vm.current.collectAsState().value
    val playing = vm.playing.collectAsState().value

    if (song != null) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color(0xFF222222))
                .clickable { onOpen() }
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = song.title,
                color = Color.White,
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = {
                if (playing) vm.pause() else vm.resume()
            }) {
                Icon(
                    imageVector = if (playing) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}
