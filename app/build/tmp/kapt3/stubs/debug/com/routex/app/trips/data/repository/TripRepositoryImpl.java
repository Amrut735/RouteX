package com.routex.app.trips.data.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.routex.app.core.utils.Resource;
import com.routex.app.trips.domain.model.Trip;
import com.routex.app.trips.domain.model.TripStatus;
import com.routex.app.trips.domain.repository.TripRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0010J,\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0096@\u00a2\u0006\u0002\u0010\u0017J\u0018\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u00192\u0006\u0010\u001a\u001a\u00020\u000fH\u0016J\u001a\u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\f0\u0019H\u0016J\u0018\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u00192\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001c\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00130\f2\u0006\u0010\u001e\u001a\u00020\u0013H\u0096@\u00a2\u0006\u0002\u0010\u001fJ$\u0010 \u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\"H\u0096@\u00a2\u0006\u0002\u0010#J\u000e\u0010$\u001a\u0004\u0018\u00010\u0013*\u00020%H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/routex/app/trips/data/repository/TripRepositoryImpl;", "Lcom/routex/app/trips/domain/repository/TripRepository;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "(Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/google/firebase/auth/FirebaseAuth;)V", "col", "Lcom/google/firebase/firestore/CollectionReference;", "getCol", "()Lcom/google/firebase/firestore/CollectionReference;", "endTrip", "Lcom/routex/app/core/utils/Resource;", "", "tripId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPagedTrips", "", "Lcom/routex/app/trips/domain/model/Trip;", "afterDocumentId", "pageSize", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeActiveTripForRoute", "Lkotlinx/coroutines/flow/Flow;", "routeId", "observeAllTrips", "observeTrip", "startTrip", "trip", "(Lcom/routex/app/trips/domain/model/Trip;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTripStatus", "status", "Lcom/routex/app/trips/domain/model/TripStatus;", "(Ljava/lang/String;Lcom/routex/app/trips/domain/model/TripStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toTrip", "Lcom/google/firebase/firestore/DocumentSnapshot;", "app_debug"})
public final class TripRepositoryImpl implements com.routex.app.trips.domain.repository.TripRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    
    @javax.inject.Inject()
    public TripRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore, @org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth auth) {
        super();
    }
    
    private final com.google.firebase.firestore.CollectionReference getCol() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object startTrip(@org.jetbrains.annotations.NotNull()
    com.routex.app.trips.domain.model.Trip trip, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.trips.domain.model.Trip>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object endTrip(@org.jetbrains.annotations.NotNull()
    java.lang.String tripId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateTripStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String tripId, @org.jetbrains.annotations.NotNull()
    com.routex.app.trips.domain.model.TripStatus status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.routex.app.trips.domain.model.Trip> observeTrip(@org.jetbrains.annotations.NotNull()
    java.lang.String tripId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.routex.app.trips.domain.model.Trip> observeActiveTripForRoute(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.routex.app.core.utils.Resource<java.util.List<com.routex.app.trips.domain.model.Trip>>> observeAllTrips() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getPagedTrips(@org.jetbrains.annotations.Nullable()
    java.lang.String afterDocumentId, int pageSize, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<? extends java.util.List<com.routex.app.trips.domain.model.Trip>>> $completion) {
        return null;
    }
    
    private final com.routex.app.trips.domain.model.Trip toTrip(com.google.firebase.firestore.DocumentSnapshot $this$toTrip) {
        return null;
    }
}