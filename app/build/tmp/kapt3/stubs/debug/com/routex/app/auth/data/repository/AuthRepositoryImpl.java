package com.routex.app.auth.data.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.routex.app.auth.domain.model.User;
import com.routex.app.auth.domain.model.UserRole;
import com.routex.app.auth.domain.repository.AuthRepository;
import com.routex.app.core.utils.Resource;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0082@\u00a2\u0006\u0002\u0010\u0011J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u000bH\u0096@\u00a2\u0006\u0002\u0010\u0013J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH\u0016J$\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00182\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0096@\u00a2\u0006\u0002\u0010\u001bJ\u001c\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00182\u0006\u0010\u001d\u001a\u00020\u0010H\u0096@\u00a2\u0006\u0002\u0010\u0011J\u000e\u0010\u001e\u001a\u00020\u001fH\u0096@\u00a2\u0006\u0002\u0010\u0013J4\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000b0\u00182\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u0010H\u0096@\u00a2\u0006\u0002\u0010#J4\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00182\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u00102\u0006\u0010%\u001a\u00020&H\u0096@\u00a2\u0006\u0002\u0010\'J$\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00100\u00182\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u0010H\u0096@\u00a2\u0006\u0002\u0010\u001bJ\u001a\u0010)\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010+0**\u00020\u000bH\u0002R\u001c\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lcom/routex/app/auth/data/repository/AuthRepositoryImpl;", "Lcom/routex/app/auth/domain/repository/AuthRepository;", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "(Lcom/google/firebase/auth/FirebaseAuth;Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/google/firebase/database/FirebaseDatabase;)V", "authState", "Lkotlinx/coroutines/flow/Flow;", "Lcom/routex/app/auth/domain/model/User;", "getAuthState", "()Lkotlinx/coroutines/flow/Flow;", "fetchUserFromFirestore", "uid", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrentUser", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isLoggedIn", "", "observeCurrentUser", "signInWithEmail", "Lcom/routex/app/core/utils/Resource;", "email", "password", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "signInWithGoogle", "idToken", "signOut", "", "signUpDriver", "displayName", "driverCode", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "signUpWithEmail", "role", "Lcom/routex/app/auth/domain/model/UserRole;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/routex/app/auth/domain/model/UserRole;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "validateDriverCode", "toMap", "", "", "app_debug"})
public final class AuthRepositoryImpl implements com.routex.app.auth.domain.repository.AuthRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth firebaseAuth = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.FirebaseDatabase database = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.routex.app.auth.domain.model.User> authState = null;
    
    @javax.inject.Inject()
    public AuthRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth firebaseAuth, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore, @org.jetbrains.annotations.NotNull()
    com.google.firebase.database.FirebaseDatabase database) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.routex.app.auth.domain.model.User> getAuthState() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object signInWithEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.auth.domain.model.User>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object signUpWithEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String displayName, @org.jetbrains.annotations.NotNull()
    com.routex.app.auth.domain.model.UserRole role, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.auth.domain.model.User>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object signInWithGoogle(@org.jetbrains.annotations.NotNull()
    java.lang.String idToken, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.auth.domain.model.User>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object validateDriverCode(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String driverCode, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<java.lang.String>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object signUpDriver(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String displayName, @org.jetbrains.annotations.NotNull()
    java.lang.String driverCode, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.auth.domain.model.User>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object signOut(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    public boolean isLoggedIn() {
        return false;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getCurrentUser(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.auth.domain.model.User> $completion) {
        return null;
    }
    
    /**
     * Observes the current user's Firestore document in real time.
     * Emits `null` when no user is signed in, or when the document is deleted.
     * Emits an updated [User] whenever the document changes (role update, etc.).
     */
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.routex.app.auth.domain.model.User> observeCurrentUser() {
        return null;
    }
    
    private final java.lang.Object fetchUserFromFirestore(java.lang.String uid, kotlin.coroutines.Continuation<? super com.routex.app.auth.domain.model.User> $completion) {
        return null;
    }
    
    private final java.util.Map<java.lang.String, java.lang.Object> toMap(com.routex.app.auth.domain.model.User $this$toMap) {
        return null;
    }
}