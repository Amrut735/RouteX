package com.routex.app.auth.domain.repository;

import com.routex.app.auth.domain.model.User;
import com.routex.app.auth.domain.model.UserRole;
import com.routex.app.core.utils.Resource;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u00a6@\u00a2\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H&J$\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u00a6@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\r2\u0006\u0010\u0013\u001a\u00020\u000fH\u00a6@\u00a2\u0006\u0002\u0010\u0014J\u000e\u0010\u0015\u001a\u00020\u0016H\u00a6@\u00a2\u0006\u0002\u0010\bJ4\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u000fH\u00a6@\u00a2\u0006\u0002\u0010\u001aJ4\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00040\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u001dH\u00a6@\u00a2\u0006\u0002\u0010\u001eJ$\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000f0\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u000fH\u00a6@\u00a2\u0006\u0002\u0010\u0011R\u001a\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006 "}, d2 = {"Lcom/routex/app/auth/domain/repository/AuthRepository;", "", "authState", "Lkotlinx/coroutines/flow/Flow;", "Lcom/routex/app/auth/domain/model/User;", "getAuthState", "()Lkotlinx/coroutines/flow/Flow;", "getCurrentUser", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isLoggedIn", "", "observeCurrentUser", "signInWithEmail", "Lcom/routex/app/core/utils/Resource;", "email", "", "password", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "signInWithGoogle", "idToken", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "signOut", "", "signUpDriver", "displayName", "driverCode", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "signUpWithEmail", "role", "Lcom/routex/app/auth/domain/model/UserRole;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/routex/app/auth/domain/model/UserRole;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "validateDriverCode", "app_debug"})
public abstract interface AuthRepository {
    
    /**
     * Emits the currently authenticated user, or null when signed out.
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.routex.app.auth.domain.model.User> getAuthState();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object signInWithEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.auth.domain.model.User>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object signInWithGoogle(@org.jetbrains.annotations.NotNull()
    java.lang.String idToken, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.auth.domain.model.User>> $completion);
    
    /**
     * Checks Firestore `drivers` collection: email must exist AND driverCode must match.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object validateDriverCode(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String driverCode, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<java.lang.String>> $completion);
    
    /**
     * Registers a new driver account after validating driverCode against Firestore.
     * Blocks if the code is invalid or the email is not pre-registered by admin.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object signUpDriver(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String displayName, @org.jetbrains.annotations.NotNull()
    java.lang.String driverCode, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.auth.domain.model.User>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object signUpWithEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String displayName, @org.jetbrains.annotations.NotNull()
    com.routex.app.auth.domain.model.UserRole role, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.auth.domain.model.User>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object signOut(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    public abstract boolean isLoggedIn();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCurrentUser(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.auth.domain.model.User> $completion);
    
    /**
     * Returns a cold Flow that emits the full [User] (with role) every time
     * the Firestore user document changes.  Useful for detecting mid-session
     * role changes (e.g., admin promotes a student to driver while they are
     * active).
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.routex.app.auth.domain.model.User> observeCurrentUser();
}