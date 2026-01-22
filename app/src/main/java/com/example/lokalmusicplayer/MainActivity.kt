package com.example.lokalmusicplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lokalmusicplayer.ui.components.MiniPlayer
import com.example.lokalmusicplayer.ui.home.HomeScreen
import com.example.lokalmusicplayer.ui.player.FullPlayerScreen
import com.example.lokalmusicplayer.ui.player.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppRoot()
        }
    }
}

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    val playerVM: PlayerViewModel = hiltViewModel()

    Scaffold(
        bottomBar = {
            MiniPlayer(
                vm = playerVM,
                onOpen = { navController.navigate("player") }
            )
        }
    ) { padding ->
        Box(Modifier.padding(padding)) {
            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable("home") {
                    HomeScreen(
                        playerVM = playerVM
                    )
                }
                composable("player") {
                    FullPlayerScreen(
                        vm = playerVM,
                        onBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}
