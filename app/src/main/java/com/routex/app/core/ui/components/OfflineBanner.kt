package com.routex.app.core.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.WifiOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private val BannerRed = Color(0xFFB71C1C)

/**
 * A slide-down banner shown at the top of a screen when the device is offline.
 * Pass [lastUpdatedMs] (epoch millis) for a human-readable "Last updated X ago" label.
 */
@Composable
fun OfflineBanner(
    isOffline: Boolean,
    lastUpdatedMs: Long = 0L,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible  = isOffline,
        enter    = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
        exit     = slideOutVertically(targetOffsetY = { -it }) + fadeOut(),
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(BannerRed)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment   = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Icon(
                imageVector        = Icons.Rounded.WifiOff,
                contentDescription = null,
                tint               = Color.White,
                modifier           = Modifier.size(18.dp),
            )
            Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
                Text(
                    text       = "Offline Mode",
                    style      = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color      = Color.White,
                )
                Text(
                    text  = if (lastUpdatedMs > 0L)
                        "Showing last known data · ${relativeTime(lastUpdatedMs)}"
                    else
                        "Showing last known data",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White.copy(alpha = 0.85f),
                )
            }
        }
    }
}

private fun relativeTime(epochMs: Long): String {
    val sec = (System.currentTimeMillis() - epochMs) / 1000L
    return when {
        sec < 5    -> "just now"
        sec < 60   -> "${sec}s ago"
        sec < 3600 -> "${sec / 60}m ago"
        else       -> "${sec / 3600}h ago"
    }
}
