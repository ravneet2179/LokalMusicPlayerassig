package com.example.lokalmusicplayer.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.lokalmusicplayer.ui.player.PlayerViewModel

@Composable
fun HomeScreen(
    playerVM: PlayerViewModel,
    vm: HomeViewModel = hiltViewModel()
) {

    var query by remember { mutableStateOf("arijit") }

    LaunchedEffect(Unit) {
        vm.search(query)
    }

    val songs by vm.songs.collectAsState()
    val loading by vm.loading.collectAsState()

    Column(Modifier.fillMaxSize()) {

        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                vm.search(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            label = { Text("Search song") }
        )

        if (loading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn {
                items(songs) { song ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable {
                                playerVM.setQueue(songs, song)
                            }
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Image(
                            painter = rememberAsyncImagePainter(song.image),
                            contentDescription = null,
                            modifier = Modifier.size(56.dp)
                        )

                        Spacer(Modifier.width(10.dp))

                        Column {
                            Text(song.title, style = MaterialTheme.typography.bodyLarge)
                            Text(song.artist, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}
