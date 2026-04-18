package com.routex.app.core.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.routex.app.trips.domain.model.TripStatus

/**
 * Color-coded status chip for a trip.
 *
 * Spec:
 *   NOT_STARTED → Grey
 *   RUNNING     → Green
 *   DELAYED     → Red
 *   COMPLETED   → Blue
 */
@Composable
fun TripStatusChip(
    status: TripStatus,
    modifier: Modifier = Modifier,
    dotSize: Dp = 8.dp,
) {
    val animatedColor by animateColorAsState(
        targetValue = status.color,
        animationSpec = tween(durationMillis = 500),
        label = "status_color",
    )
    val contentColor = Color.White

    Surface(
        modifier  = modifier,
        shape     = RoundedCornerShape(50),
        color     = animatedColor.copy(alpha = 0.15f),
        tonalElevation = 0.dp,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Box(
                modifier = Modifier
                    .size(dotSize)
                    .clip(CircleShape)
                    .background(animatedColor),
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text  = status.label,
                style = MaterialTheme.typography.labelMedium,
                color = animatedColor,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}
