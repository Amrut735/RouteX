package com.routex.app.maps.data.geofencing;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.routex.app.core.location.CollegeHub;
import com.routex.app.maps.domain.model.GeofenceEvent;
import com.routex.app.maps.domain.model.GeofenceTransition;
import com.routex.app.student.domain.model.BusStop;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.flow.SharedFlow;
import javax.inject.Inject;
import javax.inject.Singleton;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0004"}, d2 = {"GEOFENCE_LOITERING_DELAY", "", "GEOFENCE_RADIUS_M", "", "app_debug"})
public final class GeofenceManagerKt {
    public static final float GEOFENCE_RADIUS_M = 200.0F;
    private static final int GEOFENCE_LOITERING_DELAY = 10000;
}