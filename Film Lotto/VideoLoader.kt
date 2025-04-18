package com.example.filmlotto

import android.net.Uri
import android.widget.FrameLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun VideoLoader() {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(250.dp)
        ) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = {
                    val playerView = PlayerView(context).apply {
                        layoutParams = FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.MATCH_PARENT,
                            FrameLayout.LayoutParams.MATCH_PARENT
                        )
                    }

                    val player = ExoPlayer.Builder(context).build().apply {
                        val videoUri = Uri.parse("android.resource://${context.packageName}/${R.raw.animation}")
                        val mediaItem = MediaItem.fromUri(videoUri)
                        setMediaItem(mediaItem)
                        playWhenReady = true
                        repeatMode = ExoPlayer.REPEAT_MODE_ALL
                        prepare()
                    }

                    playerView.player = player
                    playerView
                }
            )
        }
    }
}
