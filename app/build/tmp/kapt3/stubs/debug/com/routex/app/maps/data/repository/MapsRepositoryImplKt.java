package com.routex.app.maps.data.repository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.routex.app.maps.domain.model.BusLocationUpdate;
import com.routex.app.maps.domain.model.MapLocation;
import com.routex.app.maps.domain.repository.MapsRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0002"}, d2 = {"BUS_LOCATIONS_NODE", "", "app_debug"})
public final class MapsRepositoryImplKt {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String BUS_LOCATIONS_NODE = "bus_locations";
}