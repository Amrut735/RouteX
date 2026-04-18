package com.routex.app.admin.data.source;

import com.google.firebase.firestore.FirebaseFirestore;
import com.routex.app.admin.domain.model.Driver;
import com.routex.app.core.utils.Resource;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010$\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u0018\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00150\n0\u0014J\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0017\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u000e\u0010\u0018\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u0019J\u001c\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000f0\n2\u0006\u0010\f\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\rJ\u001a\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001c*\u00020\u000bH\u0002R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/routex/app/admin/data/source/DriverDataSource;", "", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "(Lcom/google/firebase/firestore/FirebaseFirestore;)V", "col", "Lcom/google/firebase/firestore/CollectionReference;", "getCol", "()Lcom/google/firebase/firestore/CollectionReference;", "addDriver", "Lcom/routex/app/core/utils/Resource;", "Lcom/routex/app/admin/domain/model/Driver;", "driver", "(Lcom/routex/app/admin/domain/model/Driver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteDriver", "", "driverId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllDrivers", "Lkotlinx/coroutines/flow/Flow;", "", "getDriverByAuthUid", "uid", "seedDummyDriversIfEmpty", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateDriver", "toMap", "", "app_debug"})
public final class DriverDataSource {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    
    @javax.inject.Inject()
    public DriverDataSource(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore) {
        super();
    }
    
    private final com.google.firebase.firestore.CollectionReference getCol() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.routex.app.core.utils.Resource<java.util.List<com.routex.app.admin.domain.model.Driver>>> getAllDrivers() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addDriver(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.model.Driver driver, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.admin.domain.model.Driver>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateDriver(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.model.Driver driver, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteDriver(@org.jetbrains.annotations.NotNull()
    java.lang.String driverId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion) {
        return null;
    }
    
    /**
     * Fetch the driver record whose authUid matches the signed-in user.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getDriverByAuthUid(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.admin.domain.model.Driver>> $completion) {
        return null;
    }
    
    /**
     * Seeds 12 pre-built driver records into Firestore on first launch.
     * Uses MERGE logic — only inserts drivers whose driverCode does not already exist.
     * Existing records are never overwritten.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object seedDummyDriversIfEmpty(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.util.Map<java.lang.String, java.lang.Object> toMap(com.routex.app.admin.domain.model.Driver $this$toMap) {
        return null;
    }
}