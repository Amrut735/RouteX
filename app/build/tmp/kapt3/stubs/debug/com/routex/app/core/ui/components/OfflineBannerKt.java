package com.routex.app.core.ui.components;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a$\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001a\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0002\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0002\u00a8\u0006\u000e"}, d2 = {"BannerRed", "Landroidx/compose/ui/graphics/Color;", "J", "OfflineBanner", "", "isOffline", "", "lastUpdatedMs", "", "modifier", "Landroidx/compose/ui/Modifier;", "relativeTime", "", "epochMs", "app_debug"})
public final class OfflineBannerKt {
    private static final long BannerRed = 0L;
    
    /**
     * A slide-down banner shown at the top of a screen when the device is offline.
     * Pass [lastUpdatedMs] (epoch millis) for a human-readable "Last updated X ago" label.
     */
    @androidx.compose.runtime.Composable()
    public static final void OfflineBanner(boolean isOffline, long lastUpdatedMs, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    private static final java.lang.String relativeTime(long epochMs) {
        return null;
    }
}