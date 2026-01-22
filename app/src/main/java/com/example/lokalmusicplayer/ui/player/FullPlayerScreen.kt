package com.example.lokalmusicplayer.ui.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun FullPlayerScreen(
    vm: PlayerViewModel,
    onBack: () -> Unit
) {

    val song = vm.current.collectAsState().value
    val playing = vm.playing.collectAsState().value

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        IconButton(onClick = onBack, modifier = Modifier.align(Alignment.Start)) {
            Icon(Icons.Default.ArrowBack, contentDescription = null)
        }

        Spacer(Modifier.height(40.dp))

        if (song != null) {
            Image(
                painter = rememberAsyncImagePainter(song.image),
                contentDescription = null,
                modifier = Modifier.size(250.dp)
            )

            Spacer(Modifier.height(20.dp))

            Text(song.title, style = MaterialTheme.typography.titleLarge)
            Text(song.artist)

            Spacer(Modifier.height(40.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {

                IconButton(onClick = { vm.prev() }) {
                    Icon(Icons.Default.SkipPrevious, null)
                }

                IconButton(onClick = {
                    if (playing) vm.pause() else vm.resume()
                }) {
                    Icon(
                        if (playing) Icons.Default.Pause else Icons.Default.PlayArrow,
                        null,
                        modifier = Modifier.size(60.dp)
                    )
                }

                IconButton(onClick = { vm.next() }) {
                    Icon(Icons.Default.SkipNext, null)
                }
            }
        }
    }
}
