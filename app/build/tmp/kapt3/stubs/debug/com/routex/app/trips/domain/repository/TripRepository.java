package com.routex.app.trips.domain.repository;

import com.routex.app.core.utils.Resource;
import com.routex.app.trips.domain.model.Trip;
import com.routex.app.trips.domain.model.TripStatus;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bJ\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0007J0\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\f\u001a\u00020\rH\u00a6@\u00a2\u0006\u0002\u0010\u000eJ\u0018\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00102\u0006\u0010\u0011\u001a\u00020\u0006H&J\u001a\u0010\u0012\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u00030\u0010H&J\u0018\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00102\u0006\u0010\u0005\u001a\u00020\u0006H&J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\u0006\u0010\u0015\u001a\u00020\nH\u00a6@\u00a2\u0006\u0002\u0010\u0016J$\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0019H\u00a6@\u00a2\u0006\u0002\u0010\u001a\u00a8\u0006\u001c"}, d2 = {"Lcom/routex/app/trips/domain/repository/TripRepository;", "", "endTrip", "Lcom/routex/app/core/utils/Resource;", "", "tripId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPagedTrips", "", "Lcom/routex/app/trips/domain/model/Trip;", "afterDocumentId", "pageSize", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeActiveTripForRoute", "Lkotlinx/coroutines/flow/Flow;", "routeId", "observeAllTrips", "observeTrip", "startTrip", "trip", "(Lcom/routex/app/trips/domain/model/Trip;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTripStatus", "status", "Lcom/routex/app/trips/domain/model/TripStatus;", "(Ljava/lang/String;Lcom/routex/app/trips/domain/model/TripStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public abstract interface TripRepository {
    public static final int PAGE_SIZE = 20;
    @org.jetbrains.annotations.NotNull()
    public static final com.routex.app.trips.domain.repository.TripRepository.Companion Companion = null;
    
    /**
     * Creates a new trip document in Firestore and returns it (with generated ID).
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object startTrip(@org.jetbrains.annotations.NotNull()
    com.routex.app.trips.domain.model.Trip trip, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.trips.domain.model.Trip>> $completion);
    
    /**
     * Sets status = COMPLETED and records endTime.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object endTrip(@org.jetbrains.annotations.NotNull()
    java.lang.String tripId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion);
    
    /**
     * Updates only the status field — used for auto-delay detection.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateTripStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String tripId, @org.jetbrains.annotations.NotNull()
    com.routex.app.trips.domain.model.TripStatus status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion);
    
    /**
     * Real-time observer for a single trip (driver / student view).
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.routex.app.trips.domain.model.Trip> observeTrip(@org.jetbrains.annotations.NotNull()
    java.lang.String tripId);
    
    /**
     * Returns the active (RUNNING or DELAYED) trip for a given route, or null.
     * Used by the student map screen to show the live trip status.
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.routex.app.trips.domain.model.Trip> observeActiveTripForRoute(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId);
    
    /**
     * Real-time observer for all trips — used by the admin dashboard.
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.routex.app.core.utils.Resource<java.util.List<com.routex.app.trips.domain.model.Trip>>> observeAllTrips();
    
    /**
     * Cursor-based paginated fetch of trips ordered by [startTime] descending.
     * Pass [afterDocumentId] to start after a specific document (second page, etc.).
     * Returns at most [pageSize] trips.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPagedTrips(@org.jetbrains.annotations.Nullable()
    java.lang.String afterDocumentId, int pageSize, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<? extends java.util.List<com.routex.app.trips.domain.model.Trip>>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/routex/app/trips/domain/repository/TripRepository$Companion;", "", "()V", "PAGE_SIZE", "", "app_debug"})
    public static final class Companion {
        public static final int PAGE_SIZE = 20;
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}