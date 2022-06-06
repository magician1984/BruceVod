package idv.bruce.vod.pages

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.view.SurfaceView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.AssetDataSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DataSpec
import com.google.android.exoplayer2.util.EventLogger

@Composable
fun IdlePage() {
    val mContext: Context = LocalContext.current

    val path: String = "assets:///idle_video.mp4"

    val player =
        ExoPlayer.Builder(mContext).build().apply {
            repeatMode = ExoPlayer.REPEAT_MODE_ALL

            val uri: Uri = Uri.parse(path)

            val dataSpec: DataSpec = DataSpec(uri)

            val dataSource: AssetDataSource = AssetDataSource(mContext)

            val fac: DataSource.Factory = DataSource.Factory {
                return@Factory dataSource
            }

            val mediaSource: MediaSource = ProgressiveMediaSource.Factory(fac).createMediaSource(
                MediaItem.fromUri(uri)
            )

            dataSource.open(dataSpec)

            addMediaSource(mediaSource)

            addAnalyticsListener(EventLogger(null, "KTV_PLAYER"))
        }


    LaunchedEffect(player) {
        player.playWhenReady = true
        player.prepare()
    }

    AndroidView(factory = {
        SurfaceView(it).apply {
            player.setVideoSurfaceView(this)
        }
    })
}