package com.routex.app.core.firebase;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * One-time setup helpers called from the application or an admin screen.
 *
 * Responsibilities:
 * - Enable RTDB offline persistence (already done in AppModule, referenced here for clarity)
 * - Seed a demo route + bus_location node so the map has data to display immediately
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/routex/app/core/firebase/FirebaseInitializer;", "", "db", "Lcom/google/firebase/database/FirebaseDatabase;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "(Lcom/google/firebase/database/FirebaseDatabase;Lcom/google/firebase/firestore/FirebaseFirestore;)V", "seedDemoRoute", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public final class FirebaseInitializer {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.FirebaseDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.routex.app.core.firebase.FirebaseInitializer.Companion Companion = null;
    
    @javax.inject.Inject()
    public FirebaseInitializer(@org.jetbrains.annotations.NotNull()
    com.google.firebase.database.FirebaseDatabase db, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore) {
        super();
    }
    
    /**
     * Seeds a demo route into Firestore (idempotent — uses merge).
     * Call once from an admin screen or remove once real data exists.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object seedDemoRoute(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.jvm.JvmStatic()
    @org.jetbrains.annotations.NotNull()
    public static final java.util.Map<java.lang.String, java.lang.String> serverTimestamp() {
        return null;
    }
    
    /**
     * Returns the RTDB server timestamp map — use wherever you need server-side time.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0007\u00a8\u0006\u0006"}, d2 = {"Lcom/routex/app/core/firebase/FirebaseInitializer$Companion;", "", "()V", "serverTimestamp", "", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @kotlin.jvm.JvmStatic()
        @org.jetbrains.annotations.NotNull()
        public final java.util.Map<java.lang.String, java.lang.String> serverTimestamp() {
            return null;
        }
    }
}