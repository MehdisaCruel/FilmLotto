package com.example.filmlotto

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.filmlotto.destinations.DetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Destination
@Composable
fun DiceScreen(navigator: DestinationsNavigator) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        val dataStore = remember { FavoriteDataStore(context) }

        var movie by remember { mutableStateOf<TmdbMovie?>(null) }
        val favoriteIds by dataStore.favoriteMoviesFlow.collectAsState(initial = emptySet())

        var toastMessage by remember { mutableStateOf<String?>(null) }

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(onClick = {
                    scope.launch {
                        val result = getUpcomingMovies()
                        result?.results?.let { movies ->
                            movie = movies.random()
                            toastMessage = "سلیقت خوبه 😉"
                            delay(2000)
                            toastMessage = null
                        }
                    }
                }) {
                    Text("فیلم امشب!🎲")
                }

                Spacer(modifier = Modifier.height(24.dp))

                movie?.let {
                    Text("اینم فیلم امشب 😁", fontSize = 20.sp)

                    MovieItem(
                        movie = it,
                        buttonText = if (favoriteIds.contains(it.id.toString())) "در علاقه‌مندی‌ها هست" else "افزودن به علاقه‌مندی‌ها",
                        onFavorite = {
                            scope.launch {
                                if (!favoriteIds.contains(it.id.toString())) {
                                    dataStore.addFavorite(it.id.toString())
                                    toastMessage = "❤️ اضافه شد"
                                } else {
                                    dataStore.removeFavorite(it.id.toString())
                                    toastMessage = "🤐 حذف شد"
                                }
                                delay(2000)
                                toastMessage = null
                            }
                        },
                        onClick = {
                            navigator.navigate(DetailsScreenDestination(it))
                        }
                    )
                }
            }
            toastMessage?.let { msg ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 40.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.9f),
                        shape = MaterialTheme.shapes.medium,
                        shadowElevation = 6.dp
                    ) {
                        Text(
                            text = msg,
                            modifier = Modifier
                                .padding(horizontal = 20.dp, vertical = 10.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}